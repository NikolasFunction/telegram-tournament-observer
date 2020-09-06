package com.github.nikolasfunction.tournamentobserver.web.clicktt.factory;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.github.nikolasfunction.tournamentobserver.web.clicktt.ITournamentTableEntry;
import com.github.nikolasfunction.tournamentobserver.web.clicktt.TournamentTableEntry;

@Component
public class TournamentTableEntryFactory
        implements ITournamentTableEntryFactory {

    @Override
    public ITournamentTableEntry createTournamentTableEntry(long tournamentId,
            Date time, String url, String organizer, int freePlaces,
            int participants, int queueSize, String region, String openFor,
            String ageGroup, String pdfUrl) {

        return new TournamentTableEntry(tournamentId, time, url, organizer,
                freePlaces, participants, queueSize, region, openFor, ageGroup,
                pdfUrl);
    }

}
