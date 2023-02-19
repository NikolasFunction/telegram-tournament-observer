package com.github.nikolasfunction.tournamentobserver.telegram.controller;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.User;

import com.github.nikolasfunction.tournamentobserver.database.api.IUser;
import com.github.nikolasfunction.tournamentobserver.database.api.service.IUserService;
import com.github.nikolasfunction.tournamentobserver.telegram.annotation.BotCommand;
import com.github.nikolasfunction.tournamentobserver.telegram.annotation.BotController;
import com.github.nikolasfunction.tournamentobserver.telegram.annotation.ChatIdParameter;
import com.github.nikolasfunction.tournamentobserver.telegram.annotation.UserIdParameter;
import com.github.nikolasfunction.tournamentobserver.telegram.annotation.UserParameter;

@BotController
public class StartCommandController {
    
    private final IUserService userService;
    
    @Autowired
    public StartCommandController(IUserService userService) {
        this.userService = userService;
    }


    @BotCommand(value = "/start", description = "", hidden = true)
    public SendMessage startCommand(
            @UserParameter User user,
            @ChatIdParameter long chatId,
            @UserIdParameter int userId) {
        
        
        Optional<IUser> foundUser = userService.getUser(user.getId());
        
        if(foundUser.isPresent()) {
            return new SendMessage(chatId, "Welcome back, " + foundUser.get().getName() + "!");
        }
        
        userService.createUser(new com.github.nikolasfunction.tournamentobserver.model.User(user.getId(), user.getUserName(), new ArrayList<>()));
        
        return new SendMessage(chatId, "Welcome, " + user.getUserName() + "!");
    }

}
