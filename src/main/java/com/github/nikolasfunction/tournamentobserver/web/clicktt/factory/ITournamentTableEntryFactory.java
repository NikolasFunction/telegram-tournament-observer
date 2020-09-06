package com.github.nikolasfunction.tournamentobserver.web.clicktt.factory;

import java.util.Date;

import com.github.nikolasfunction.tournamentobserver.web.clicktt.ITournamentTableEntry;

public interface ITournamentTableEntryFactory {

    public ITournamentTableEntry createTournamentTableEntry(Date time,
            String url, String organizer, int freePlaces, int participants,
            int queueSize, String region, String openFor, String ageGroup,
            String pdfUrl);

}
