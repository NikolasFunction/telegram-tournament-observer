package com.github.nikolasfunction.tournamentobserver.web.clicktt;

import java.util.Date;

public interface ITournamentTableEntry {
    
    public Date getTime();
    
    public String getUrl();
    
    public String getOrganizer();
    
    public int getFreePlaces();
    
    public int getParticipants();
    
    public int getQueueSize();
    
    public String getRegion();
    
    public String getOpenFor();
    
    public String getAgeGroup();
    
    public String getPdfUrl();

}
