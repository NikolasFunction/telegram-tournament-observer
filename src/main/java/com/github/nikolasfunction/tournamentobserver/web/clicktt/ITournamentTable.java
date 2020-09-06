package com.github.nikolasfunction.tournamentobserver.web.clicktt;

import java.time.Month;
import java.time.Year;
import java.util.List;

public interface ITournamentTable {
    
    public Month getMonth();
    
    public Year getYear();
    
    public List<ITournamentTableEntry> getTournamentTableEntries();

}
