package com.github.nikolasfunction.tournamentobserver.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.github.nikolasfunction.tournamentobserver.exception.ConnectionException;
import com.github.nikolasfunction.tournamentobserver.exception.ParseException;

@ComponentScan(basePackages = {"com.github.nikolasfunction.tournamentobserver"})
@SpringBootApplication
public class Main {

    public static void main(String[] args) throws ConnectionException, ParseException {
        SpringApplication.run(Main.class, args);
    }

}
