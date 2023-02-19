package com.github.nikolasfunction.tournamentobserver.telegram;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.service.StateMachineService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.BotSession;
import org.telegram.telegrambots.starter.AfterBotRegistration;

import com.github.nikolasfunction.tournamentobserver.exception.ConfigurationException;
import com.github.nikolasfunction.tournamentobserver.telegram.annotation.BotController;
import com.github.nikolasfunction.tournamentobserver.telegram.annotation.ChatIdParameter;
import com.github.nikolasfunction.tournamentobserver.telegram.annotation.ChatParameter;
import com.github.nikolasfunction.tournamentobserver.telegram.annotation.CommandArgumentParameter;
import com.github.nikolasfunction.tournamentobserver.telegram.annotation.MessageIdParameter;
import com.github.nikolasfunction.tournamentobserver.telegram.annotation.UserIdParameter;
import com.github.nikolasfunction.tournamentobserver.telegram.annotation.UserParameter;
import com.github.nikolasfunction.tournamentobserver.telegram.state.UserEvent;
import com.github.nikolasfunction.tournamentobserver.telegram.state.UserState;

@Component
public class TournamentObserverBot extends TelegramLongPollingBot {
    
    private static final Logger log = LoggerFactory.getLogger(TournamentObserverBot.class);

    @Autowired
    private ApplicationContext applicationContext;
    
    @Autowired
    private StateMachineService<UserState, UserEvent> stateMachineService;

    private Map<String, Method> botCommandMethods;
    private Map<Method, Object> botCommandInstances = new HashMap<>();
    private Map<Parameter, Function<Message, Object>> parameterFunctions;
    
    private final String botToken;

    public TournamentObserverBot(
            @Value("${telegram.apitoken}") String botToken) throws TelegramApiException {
        this.botToken = botToken;

    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void onUpdateReceived(Update update) {
        
        Message message = update.getMessage();
        
        if(message.hasText()) {
            
            String text = message.getText();            
            if (text.startsWith("/")) {
                
                String[] strings = text.split(" ", 2);
                String command = strings[0];

                Method method = botCommandMethods.get(command);
                if (method != null) {

                    Object instance = botCommandInstances.get(method);
                    Object[] parameter = getParameters(method, message);
                    
                    Object returnValue = null;
                    try {
                        returnValue = method.invoke(instance, parameter);
                    } catch (IllegalAccessException 
                            | IllegalArgumentException
                            | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                    
                    if(returnValue != null) {
                        try {
                            super.execute((BotApiMethod) returnValue);
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            else {
                
//                StateMachine<UserState, UserEvent> stateMachine = stateMachineService.acquireStateMachine(Integer.toString(message.getFrom().getId()));
//                
//                if(stateMachine.sendEvent(UserEvent.COMMAND_GREET)) {
//                        
//                    
//                }
                
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "idk";
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
    
    @AfterBotRegistration
    public void sendCommands(BotSession botSession) {

        try {
            botCommandMethods = applicationContext.getBeansWithAnnotation(BotController.class).values().stream()
                    .map(object -> object.getClass().getDeclaredMethods())
                    .flatMap(object -> Stream.of(object))
                    .filter(methode -> methode.isAnnotationPresent(com.github.nikolasfunction.tournamentobserver.telegram.annotation.BotCommand.class))
                    .collect(Collectors.toMap(
                            method -> method.getAnnotation(com.github.nikolasfunction.tournamentobserver.telegram.annotation.BotCommand.class).value(),
                            method -> method));
        } catch (IllegalStateException e) {
            log.error(e.getMessage(), e);
        }
        
        botCommandMethods.values().stream()
                .filter(method -> !method.getReturnType().equals(Void.TYPE) && !BotApiMethod.class.isAssignableFrom(method.getReturnType()))
                .forEach(method -> {
                    
                    ConfigurationException exception = new ConfigurationException(String.format("Unsupported return type in Method '%s.%s'. Must be void or a subtype of '%s'. Actual type: '%s'",
                            method.getDeclaringClass().getName(),
                            method.getName(),
                            BotApiMethod.class.getName(),
                            method.getReturnType().getName()));
                    log.error(exception.getMessage(), exception);
                    
                });
        
        botCommandMethods.values().stream()
                .forEach(method -> Stream.of(method.getParameters())
                    .filter(parameter -> AnnotationUtils.findAnnotation(parameter, com.github.nikolasfunction.tournamentobserver.telegram.annotation.Parameter.class) == null)
                    .forEach(parameter -> {
                        
                        ConfigurationException exception = new ConfigurationException(String.format("Unsupported or Missing annotation of parameter '%s' in Method '%s.%s'. Must be annotated with annotation compatible to '%s'.",
                                parameter.getName(),
                                method.getDeclaringClass().getName(),
                                method.getName(),
                                com.github.nikolasfunction.tournamentobserver.telegram.annotation.Parameter.class.getName()));
                        log.error(exception.getMessage(), exception);
                    }));
        
        botCommandMethods.values().stream().forEach(method -> Stream.of(method.getParameters())
            .filter(parameter -> Stream.of(AnnotationUtils.findAnnotation(parameter, com.github.nikolasfunction.tournamentobserver.telegram.annotation.Parameter.class).value())
                    .noneMatch(parameter.getType()::equals))
            .forEach(parameter -> {
                
                    ConfigurationException exception = new ConfigurationException(String.format("Unsupported parameter type of parameter '%s' in Method '%s.%s'. Must be one of the following: '%s'. Actual type: '%s'",
                          parameter.getName(),
                          method.getDeclaringClass().getName(),
                          method.getName(),
                          Stream.of(AnnotationUtils.findAnnotation(parameter, com.github.nikolasfunction.tournamentobserver.telegram.annotation.Parameter.class).value())
                                  .map(supportedType -> supportedType.getName())
                                  .collect(Collectors.joining(", ", "[", "]")),
                                  parameter.getType().getName()
                          ));
                    log.error(exception.getMessage(), exception);
                    
            }));
        
        Map<Class<? extends Annotation>, Function<Message, Object>> annotationLogic = Map.of(
                ChatIdParameter.class,          (Message message) -> message.getChatId(),
                ChatParameter.class,            (Message message) -> message.getChat(),
                CommandArgumentParameter.class, (Message message) -> {
                                                    String[] strings = message.getText().split(" ", 2);
                                                    return strings.length < 2 ? "" : strings[1];
                                                },
                UserIdParameter.class,          (Message message) -> message.getFrom().getId(),
                UserParameter.class,            (Message message) -> message.getFrom(),
                MessageIdParameter.class,       (Message message) -> message.getMessageId()
        );
        
        parameterFunctions = botCommandMethods.values().stream()
                .flatMap(method -> Stream.of(method.getParameters()))
                .collect(Collectors.toMap(parameter -> parameter, parameter -> {
                    
                    Optional<Function<Message, Object>> function = annotationLogic.entrySet().stream()
                            .filter(functionSet -> parameter.isAnnotationPresent(functionSet.getKey()))
                            .map(functionSet -> functionSet.getValue())
                            .findAny();
                    
                    if(function.isEmpty()) {
                        ConfigurationException exception = new ConfigurationException(String.format("Parameter annotation of parameter '%s' in Method '%s.%s' not completely implemented.",
                                parameter.getName(),
                                parameter.getDeclaringExecutable().getDeclaringClass().getName(),
                                parameter.getDeclaringExecutable().getName()));
                        log.error(exception.getMessage(), exception);
                        return null;
                    }
                    
                    return function.get();
                }));

        for (Object botController : applicationContext.getBeansWithAnnotation(BotController.class).values()) {

            Class<?> clazz = botController.getClass();
            if (AopUtils.isAopProxy(botController)) {
                clazz = AopUtils.getTargetClass(botController);
            }

            Stream.of(clazz.getDeclaredMethods())
                    .filter(method -> method.isAnnotationPresent(com.github.nikolasfunction.tournamentobserver.telegram.annotation.BotCommand.class))
                    .forEach(method -> botCommandInstances.put(method, botController));
        }

        SetMyCommands setMyCommands = new SetMyCommands(botCommandMethods.values().stream()
                .map(method -> method.getAnnotation(com.github.nikolasfunction.tournamentobserver.telegram.annotation.BotCommand.class))
                .filter(command -> !command.hidden())
                .map(botCommand -> new BotCommand(botCommand.value(),botCommand.description()))
                .collect(Collectors.toList()));
        try {
            super.execute(setMyCommands);
        } catch (TelegramApiException e) {
            log.error(e.getMessage(), e);
        }

    }
    
    private Object[] getParameters(Method method, Message message) {
        
        return Stream.of(method.getParameters())
                .map(parameter -> {
                    
                    Function<Message, Object> parameterFunction = parameterFunctions.get(parameter);                    
                    if(parameterFunction == null) {
                        
                        ConfigurationException exception = new ConfigurationException(String.format("Parameter annotation of parameter '%s' in Method '%s.%s' not completely implemented.",
                                parameter.getName(),
                                method.getDeclaringClass().getName(),
                                method.getName()));
                        log.error(exception.getMessage(), exception);                        
                        return null;
                    }
                    return parameterFunction.apply(message);
                })
                .toArray();
    }
}
