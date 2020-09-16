package com.github.nikolasfunction.tournamentobserver.telegram.controller;

import java.util.ArrayList;
import java.util.List;

import org.telegram.telegrambots.meta.api.methods.send.SendDice;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import com.github.nikolasfunction.tournamentobserver.telegram.annotation.BotCommand;
import com.github.nikolasfunction.tournamentobserver.telegram.annotation.BotController;
import com.github.nikolasfunction.tournamentobserver.telegram.annotation.ChatIdParameter;
import com.github.nikolasfunction.tournamentobserver.telegram.annotation.MessageIdParameter;

@BotController
public class TelegramBotController {
    
    @BotCommand(value = "/help", description = "Helps you")
    public SendMessage helpCommand(
            @ChatIdParameter long chatId,
            @MessageIdParameter int messageId) {
        
        System.out.println("Help received!");
        
        SendMessage message = new SendMessage(chatId, "Help yourself!");
        message.setReplyToMessageId(messageId);
        
        return message;
        
    }
    
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
    
    @BotCommand(value = "/test", description = "Tests the keyboard functionality")
    public SendMessage testKeyboard(
            @ChatIdParameter long chatId,
            @MessageIdParameter int messageId) {
        
        List<KeyboardRow> rows = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        row.addAll(List.of("First item", "second item"));
        rows.add(row);
        
        row = new KeyboardRow();
        row.addAll(List.of("Another choice", "Don't press me"));
        rows.add(row);
        
        ReplyKeyboardMarkup customKeyboard = new ReplyKeyboardMarkup();
        customKeyboard.setKeyboard(rows);
        
        SendMessage message = new SendMessage(chatId, "Help yourself!");
        message.setReplyMarkup(customKeyboard);
        message.setReplyToMessageId(messageId);
        
        return message;
    }
    
    

}
