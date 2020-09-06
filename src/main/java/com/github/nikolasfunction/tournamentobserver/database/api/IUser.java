package com.github.nikolasfunction.tournamentobserver.database.api;

import java.util.List;

public interface IUser {
    
    public long getUserId();
    
    public long getChatId();
    
    public List<ITournament> getObservedTournaments();
    
}
