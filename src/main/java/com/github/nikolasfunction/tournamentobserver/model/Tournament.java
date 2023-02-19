package com.github.nikolasfunction.tournamentobserver.model;

import java.util.Date;

import com.github.nikolasfunction.tournamentobserver.database.api.ITournament;

public class Tournament implements ITournament {
    
    private final long id;
    
    private final Date time;
    
    private final String url;
    
    private final int participants;
    
    private final int freePlaces;
    
    public Tournament(long id, Date time, String url, int participants,
            int freePlaces) {
        this.id = id;
        this.time = time;
        this.url = url;
        this.participants = participants;
        this.freePlaces = freePlaces;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public Date getTime() {
        return time;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public int getParticipants() {
        return participants;
    }

    @Override
    public int getFreePlaces() {
        return freePlaces;
    }

}
