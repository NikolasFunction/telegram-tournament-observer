package com.github.nikolasfunction.tournamentobserver.scanner;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.nikolasfunction.tournamentobserver.database.api.ITournament;
import com.github.nikolasfunction.tournamentobserver.database.api.service.ITournamentService;

@Component
public class TableInitializer implements ITableInitializer {
    
    private final ITournamentService tournamentService;
    
    private final IClickTtScanner clickTtScanner;
    
    @Autowired
    public TableInitializer(ITournamentService tournamentService,
            IClickTtScanner clickTtScanner) {
        this.tournamentService = tournamentService;
        this.clickTtScanner = clickTtScanner;
    }



    @Override
    public void initializeTable() {
        
        if(tournamentService.getNumberOfTournaments() < 1) {
            List<ITournament> tournaments = clickTtScanner.scanClickTt();
            tournamentService.createTournaments(tournaments);
        }
        
    }
    
    

}
