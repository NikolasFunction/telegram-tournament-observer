package com.github.nikolasfunction.tournamentobserver.web.clicktt;

import java.time.Month;
import java.time.Year;
import java.util.List;

public class TournamentTable implements ITournamentTable {
    
    private Month month;
    private Year year;
    private List<ITournamentTableEntry> tournamentTableEntries;
    
    public TournamentTable(Month month, Year year,
            List<ITournamentTableEntry> tournamentTableEntries) {
        super();
        this.month = month;
        this.year = year;
        this.tournamentTableEntries = tournamentTableEntries;
    }
    
    @Override
    public Month getMonth() {
        return month;
    }
    
    @Override
    public Year getYear() {
        return year;
    }
    
    @Override
    public List<ITournamentTableEntry> getTournamentTableEntries() {
        return tournamentTableEntries;
    }

}
