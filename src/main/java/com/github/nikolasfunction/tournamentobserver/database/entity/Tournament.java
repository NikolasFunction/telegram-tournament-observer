package com.github.nikolasfunction.tournamentobserver.database.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Tournament {
    
    @Id
    private long id;
    
    @Temporal(TemporalType.DATE)
    private Date time;
    
    private String url;
    
    private int participants;
    
    private int freePlaces;
    
    public Tournament() {
        
    }
    
    public Tournament(long tournamentId, Date time, String url,
            int participants, int freePlaces) {
        this.id = tournamentId;
        this.time = time;
        this.url = url;
        this.participants = participants;
        this.freePlaces = freePlaces;
    }



    public long getId() {
        return id;
    }

    public void setId(long tournamentId) {
        this.id = tournamentId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getParticipants() {
        return participants;
    }

    public void setParticipants(int participants) {
        this.participants = participants;
    }

    public int getFreePlaces() {
        return freePlaces;
    }

    public void setFreePlaces(int freePlaces) {
        this.freePlaces = freePlaces;
    }
    

}
