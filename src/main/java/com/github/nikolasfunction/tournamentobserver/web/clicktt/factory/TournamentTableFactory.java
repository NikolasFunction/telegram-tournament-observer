package com.github.nikolasfunction.tournamentobserver.web.clicktt.factory;

import java.time.Month;
import java.time.Year;
import java.util.List;

import com.github.nikolasfunction.tournamentobserver.web.clicktt.ITournamentTable;
import com.github.nikolasfunction.tournamentobserver.web.clicktt.ITournamentTableEntry;
import com.github.nikolasfunction.tournamentobserver.web.clicktt.TournamentTable;

public class TournamentTableFactory implements ITournamentTableFactory {

    @Override
    public ITournamentTable createTournamentTable(Month month, Year year,
            List<ITournamentTableEntry> tournamentTableEntries) {
        
        return new TournamentTable(month, year, tournamentTableEntries);
    }

}
