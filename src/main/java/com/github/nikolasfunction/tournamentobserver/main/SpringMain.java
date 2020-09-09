package com.github.nikolasfunction.tournamentobserver.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.github.nikolasfunction.tournamentobserver.scanner.ITableInitializer;

@Component
public class SpringMain implements CommandLineRunner {
    
    private final ITableInitializer tableInitializer;
    
    @Autowired
    public SpringMain(ITableInitializer tableInitializer) {
        this.tableInitializer = tableInitializer;
    }
    
    @Override
    public void run(String... args) throws Exception {
        tableInitializer.initializeTable();
    }
    
    

}
