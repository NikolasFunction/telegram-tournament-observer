package com.github.nikolasfunction.tournamentobserver.database.api;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ITournamentService {
    
    public void createTournament(ITournament tournament);
    
    public void createTournaments(Collection<ITournament> tournaments);
    
    public List<ITournament> getTournaments();
    
    public int getNumberOfTournaments();
    
    public Optional<ITournament> getTournament(long tournamentId);

}
