package com.github.nikolasfunction.tournamentobserver.telegram.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.data.RepositoryStateMachine;
import org.springframework.statemachine.data.jpa.JpaRepositoryStateMachine;
import org.springframework.statemachine.service.StateMachineService;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import com.github.nikolasfunction.tournamentobserver.telegram.annotation.BotCommand;
import com.github.nikolasfunction.tournamentobserver.telegram.annotation.BotController;
import com.github.nikolasfunction.tournamentobserver.telegram.annotation.ChatIdParameter;
import com.github.nikolasfunction.tournamentobserver.telegram.annotation.UserIdParameter;
import com.github.nikolasfunction.tournamentobserver.telegram.state.UserEvent;
import com.github.nikolasfunction.tournamentobserver.telegram.state.UserState;

@BotController

public class TestCommandController {
    
    @Autowired
    private StateMachineService<UserState, UserEvent> stateMachineService;
    
    @BotCommand(value = "/greet", description = "Greets someone")
    public SendMessage testKeyboard(
            @ChatIdParameter long chatId,
            @UserIdParameter int userId) {
        
//        stateMachineService.
        
        // first step: Is user in a state?
        StateMachine<UserState, UserEvent> stateMachine = stateMachineService.acquireStateMachine(Integer.toString(userId));
        
        String msg;
        
        if(stateMachine.sendEvent(UserEvent.COMMAND_GREET)) {
            msg = "Which person would you like to greet?";
        }
        else {
            msg = "Not in this state";
        }
        
        SendMessage message = new SendMessage(chatId, msg);
        
        return message;
    }
    
    
    

}
