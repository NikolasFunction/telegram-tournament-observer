package com.github.nikolasfunction.tournamentobserver.database.api;

import java.util.List;

public interface IUser {
    
    public int getId();
    
    public String getName();
    
    public List<ITournament> getObservedTournaments();
    
}
