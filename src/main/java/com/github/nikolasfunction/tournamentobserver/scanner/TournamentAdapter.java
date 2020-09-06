package com.github.nikolasfunction.tournamentobserver.scanner;

import java.util.Date;

import com.github.nikolasfunction.tournamentobserver.database.api.ITournament;
import com.github.nikolasfunction.tournamentobserver.web.clicktt.ITournamentTableEntry;

public class TournamentAdapter implements ITournament {
    
    private final ITournamentTableEntry tournamentTableEntry;

    public TournamentAdapter(ITournamentTableEntry tournamentTableEntry) {
        this.tournamentTableEntry = tournamentTableEntry;
    }

    @Override
    public long getTournamentId() {
        return tournamentTableEntry.getTournamentId();
    }

    @Override
    public Date getTime() {
        return tournamentTableEntry.getTime();
    }

    @Override
    public String getUrl() {
        return tournamentTableEntry.getUrl();
    }

    @Override
    public int getParticipants() {
        return tournamentTableEntry.getParticipants();
    }

    @Override
    public int getFreePlaces() {
        return tournamentTableEntry.getFreePlaces();
    }

}
