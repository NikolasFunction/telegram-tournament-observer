package com.github.nikolasfunction.tournamentobserver.model;

import java.util.List;

import com.github.nikolasfunction.tournamentobserver.database.api.ITournament;
import com.github.nikolasfunction.tournamentobserver.database.api.IUser;

public class User implements IUser {
    
    private final int id;
    
    private final String name;
    
    private final List<ITournament> observerdTournaments;
    
    public User(int id, String name, List<ITournament> observerdTournaments) {
        this.id = id;
        this.name = name;
        this.observerdTournaments = observerdTournaments;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<ITournament> getObservedTournaments() {
        return observerdTournaments;
    }

}
