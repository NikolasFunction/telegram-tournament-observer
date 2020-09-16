package com.github.nikolasfunction.tournamentobserver.telegram.bot.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.nikolasfunction.tournamentobserver.telegram.annotation.BotCommand;
import com.github.nikolasfunction.tournamentobserver.telegram.annotation.BotController;
import com.github.nikolasfunction.tournamentobserver.telegram.bot.ITelegramBot;

@BotController
public class TelegramBotController {
    
    private final ITelegramBot telegramBot;
    
    @Autowired
    public TelegramBotController(ITelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    @BotCommand(value = "/help", description = "Helps you")
    public void helpCommand(String arguments, int chatId) {
        System.out.println("Help received!");
        
    }
    
    @BotCommand(value = "/anotherCommand", description = "Hidden command")
    public void anotherCommand(String arguments, int chatId) {
        System.out.println("AnotherCommand received!");
    }
    
    

}
