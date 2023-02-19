package com.github.nikolasfunction.tournamentobserver.database.api;

import java.util.Date;

public interface ITournament {
    
    public long getId();
    
    public Date getTime();
    
    public String getUrl();
    
    public int getParticipants();
    
    public int getFreePlaces();
    
}