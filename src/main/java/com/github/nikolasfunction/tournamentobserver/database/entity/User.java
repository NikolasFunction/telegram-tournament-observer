package com.github.nikolasfunction.tournamentobserver.database.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {
    
    @Id
    private int id;
    
    private String name;
    
    @OneToMany
    private Set<Tournament> observedTournaments;
    
    public User() {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Tournament> getObservedTournaments() {
        return observedTournaments;
    }

    public void setObservedTournaments(Set<Tournament> observedTournaments) {
        this.observedTournaments = observedTournaments;
    }

}
