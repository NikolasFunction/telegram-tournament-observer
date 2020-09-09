package com.github.nikolasfunction.tournamentobserver.main;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;

import com.github.nikolasfunction.tournamentobserver.exception.ConnectionException;
import com.github.nikolasfunction.tournamentobserver.exception.ParseException;

@SpringBootApplication
@ComponentScan(basePackages = {"com.github.nikolasfunction.tournamentobserver"})
@EnableJpaRepositories(basePackages = {"com.github.nikolasfunction.tournamentobserver.database"})
@EntityScan(basePackages = {"com.github.nikolasfunction.tournamentobserver.database"})
public class Main {

    public static void main(String[] args) throws ConnectionException, ParseException {
        ApiContextInitializer.init();
        SpringApplication.run(Main.class, args);
    }
    
}
