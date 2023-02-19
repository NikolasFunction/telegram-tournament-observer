package com.github.nikolasfunction.tournamentobserver.database.api.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.github.nikolasfunction.tournamentobserver.database.api.ITournament;

public interface ITournamentService {
    
    public void createTournament(ITournament tournament);
    
    public void createTournaments(Collection<ITournament> tournaments);
    
    public List<ITournament> getTournaments();
    
    public int getNumberOfTournaments();
    
    public Optional<ITournament> getTournament(long tournamentId);

}
