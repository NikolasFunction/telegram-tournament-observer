package com.github.nikolasfunction.tournamentobserver.telegram.controller;

import org.telegram.telegrambots.meta.api.methods.send.SendDice;

import com.github.nikolasfunction.tournamentobserver.telegram.annotation.BotCommand;
import com.github.nikolasfunction.tournamentobserver.telegram.annotation.BotController;
import com.github.nikolasfunction.tournamentobserver.telegram.annotation.ChatIdParameter;
import com.github.nikolasfunction.tournamentobserver.telegram.annotation.MessageIdParameter;

@BotController
public class DiceCommandController {
    
    @BotCommand(value = "/dice", description = "Hidden command - throws a dice", hidden = true)
    public SendDice anotherCommand(
            @ChatIdParameter long chatId,
            @MessageIdParameter int messageId) {
        
        System.out.println("AnotherCommand received!");
        
        SendDice dice = new SendDice();
        dice.setChatId(chatId);
        dice.setEmoji("🎲");
        dice.setReplyToMessageId(messageId);
        
        return dice;
    }

}
