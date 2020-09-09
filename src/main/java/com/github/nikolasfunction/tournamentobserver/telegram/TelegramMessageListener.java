package com.github.nikolasfunction.tournamentobserver.telegram;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.aop.support.AopUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.github.nikolasfunction.tournamentobserver.exception.ConfigurationException;
import com.github.nikolasfunction.tournamentobserver.telegram.annotation.BotCommand;
import com.github.nikolasfunction.tournamentobserver.telegram.annotation.BotController;

@Component
public class TelegramMessageListener { // implements UpdatesListener {

    private Map<String, Method> botCommandMethods;
    private Map<Method, Object> botCommandInstances = new HashMap<>();
    
    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) throws ConfigurationException {
        
        ApplicationContext appContext = event.getApplicationContext();
        
        try {
            botCommandMethods = appContext.getBeansWithAnnotation(BotController.class).values().stream()
                    .map(object -> object.getClass().getDeclaredMethods())
                    .flatMap(object -> Stream.of(object))
                    .filter(methode -> methode.isAnnotationPresent(BotCommand.class))
                    .collect(Collectors.toMap(
                            methode -> methode.getAnnotation(BotCommand.class).value(),
                            methode -> methode));
        }
        catch(IllegalStateException e) {
            throw new ConfigurationException(e);
        }
        
        Optional<Method> methodWithUnsupportedReturnType = botCommandMethods.values().stream()
            .filter(method -> !method.getReturnType().equals(Void.TYPE))
            .findAny();
        if(methodWithUnsupportedReturnType.isPresent()) {
            throw new ConfigurationException("Unsupported return type in Method '" + methodWithUnsupportedReturnType.get().getName() + "'. Must be void. Actual type: " + methodWithUnsupportedReturnType.get().getReturnType().getName());
        }
        
        for(Object botController : appContext.getBeansWithAnnotation(BotController.class).values()) {
            
            Class<?> clazz = botController.getClass();
            if(AopUtils.isAopProxy(botController)) {
                clazz = AopUtils.getTargetClass(botController);
            }
            
            Stream.of(clazz.getDeclaredMethods())
                    .filter(method -> method.isAnnotationPresent(BotCommand.class))
                    .forEach(method -> botCommandInstances.put(method, botController));
        }
    }
    
    
//    @Override
//    public int process(List<Update> updates) {
//        
//        System.out.println("process");
//
//        updates.forEach(update -> {
//    
//            String message = update.message().text();
//            if(message.startsWith("/")) {
//                
//                String[] strings = message.split(" ", 2);
//                String command = strings[0];
//                String arguments = strings.length < 2 ? "" : strings[1];
//                int chatId = update.message().from().id();
//                
//                Method method = botCommandMethods.get(command);
//                if(method != null) {
//                    
//                    Object instance = botCommandInstances.get(method);
//                    try {
//                        method.invoke(instance, arguments, chatId);
//                    } catch (IllegalAccessException | IllegalArgumentException
//                            | InvocationTargetException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        });
//        return UpdatesListener.CONFIRMED_UPDATES_ALL;
//    }

}
