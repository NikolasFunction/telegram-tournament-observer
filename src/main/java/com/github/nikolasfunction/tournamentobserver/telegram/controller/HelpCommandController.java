package com.github.nikolasfunction.tournamentobserver.telegram.controller;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import com.github.nikolasfunction.tournamentobserver.telegram.annotation.BotCommand;
import com.github.nikolasfunction.tournamentobserver.telegram.annotation.BotController;
import com.github.nikolasfunction.tournamentobserver.telegram.annotation.ChatIdParameter;
import com.github.nikolasfunction.tournamentobserver.telegram.annotation.MessageIdParameter;

@BotController
public class HelpCommandController {
    
    @BotCommand(value = "/help", description = "Helps you")
    public SendMessage helpCommand(
            @ChatIdParameter long chatId,
            @MessageIdParameter int messageId) {
        
        System.out.println("Help received!");
        
        SendMessage message = new SendMessage(chatId, "Help yourself!");
        message.setReplyToMessageId(messageId);
        
        return message;
        
    }

}
