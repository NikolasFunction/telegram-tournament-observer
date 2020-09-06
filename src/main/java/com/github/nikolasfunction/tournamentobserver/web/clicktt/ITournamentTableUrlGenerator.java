package com.github.nikolasfunction.tournamentobserver.web.clicktt;

import java.time.Month;
import java.time.Year;

public interface ITournamentTableUrlGenerator {
    
    public String generateTournamentTableUrl(Month month, Year year);

}
