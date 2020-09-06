package com.github.nikolasfunction.tournamentobserver.database.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;
    
    private long chatId;
    
    @OneToMany
    private Set<Tournament> observedTournaments;
    
    public User() {
        
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }

    public Set<Tournament> getObservedTournaments() {
        return observedTournaments;
    }

    public void setObservedTournaments(Set<Tournament> observedTournaments) {
        this.observedTournaments = observedTournaments;
    }
    
    
    

}
