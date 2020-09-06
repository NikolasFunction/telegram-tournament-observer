package com.github.nikolasfunction.tournamentobserver.database.service;

import java.util.Date;

import com.github.nikolasfunction.tournamentobserver.database.api.ITournament;
import com.github.nikolasfunction.tournamentobserver.database.entity.Tournament;

public class TournamentAdapter implements ITournament {
    
    private final Tournament tournament;
    
    public TournamentAdapter(Tournament tournament) {
        this.tournament = tournament;
    }

    @Override
    public long getTournamentId() {
        return tournament.getTournamentId();
    }

    @Override
    public Date getTime() {
        return tournament.getTime();
    }

    @Override
    public String getUrl() {
        return tournament.getUrl();
    }

    @Override
    public int getParticipants() {
        return tournament.getParticipants();
    }

    @Override
    public int getFreePlaces() {
        return tournament.getFreePlaces();
    }

}
