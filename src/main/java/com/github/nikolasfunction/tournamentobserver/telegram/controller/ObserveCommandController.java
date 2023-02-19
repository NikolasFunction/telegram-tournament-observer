package com.github.nikolasfunction.tournamentobserver.telegram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.service.StateMachineService;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import com.github.nikolasfunction.tournamentobserver.telegram.annotation.BotCommand;
import com.github.nikolasfunction.tournamentobserver.telegram.annotation.BotController;
import com.github.nikolasfunction.tournamentobserver.telegram.annotation.BotText;
import com.github.nikolasfunction.tournamentobserver.telegram.annotation.ChatIdParameter;
import com.github.nikolasfunction.tournamentobserver.telegram.annotation.TextParameter;
import com.github.nikolasfunction.tournamentobserver.telegram.state.UserEvent;
import com.github.nikolasfunction.tournamentobserver.telegram.state.UserState;

@BotController
public class ObserveCommandController {
    
    private final StateMachineService<UserState, UserEvent> stateMachineService;
    
    @Autowired
    public ObserveCommandController(
            StateMachineService<UserState, UserEvent> stateMachineService) {
        this.stateMachineService = stateMachineService;
    }



    @BotCommand(value = "/observe", description = "Lets you observe a tournament")
    public SendMessage helpCommand(
            @ChatIdParameter long chatId) {
        
        SendMessage message = new SendMessage(chatId, "Please put in the link to the tournament. Alternatively, you can text the ID of the tournament.");
        
        System.out.println("Help received!");
        
        
        return message;
        
    }
    
    @BotText(state = UserState.STATE_INIT, event = UserEvent.COMMAND_OBSERVE)
    public SendMessage linkInput(
            @TextParameter String text,
            @ChatIdParameter long chatId) {
        return null;
        
    }

}
