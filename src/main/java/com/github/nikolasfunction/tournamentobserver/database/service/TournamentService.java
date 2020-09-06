package com.github.nikolasfunction.tournamentobserver.database.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.nikolasfunction.tournamentobserver.database.api.ITournament;
import com.github.nikolasfunction.tournamentobserver.database.api.ITournamentService;
import com.github.nikolasfunction.tournamentobserver.database.entity.Tournament;
import com.github.nikolasfunction.tournamentobserver.database.repository.ITournamentRepository;

@Service
public class TournamentService implements ITournamentService {
    
    private final ITournamentRepository tournamentRepository;
    
    @Autowired
    public TournamentService(ITournamentRepository tournamentRepository) {
        this.tournamentRepository = tournamentRepository;
    }



    @Override
    public void createTournament(ITournament tournament) {
        
        Tournament tournamentEntity = new Tournament();
        tournamentEntity.setFreePlaces(tournament.getFreePlaces());
        tournamentEntity.setParticipants(tournament.getParticipants());
        tournamentEntity.setTime(tournament.getTime());
        tournamentEntity.setTournamentId(tournament.getTournamentId());
        tournamentEntity.setUrl(tournament.getUrl());
        
        tournamentRepository.saveAndFlush(tournamentEntity);
      
        
    }

    @Override
    public List<ITournament> getTournaments() {
        
        return tournamentRepository.findAll().stream()
            .map(tournament -> new TournamentAdapter(tournament))
            .collect(Collectors.toList());
    }


    @Override
    public Optional<ITournament> getTournament(long tournamentId) {
        Optional<Tournament> foundTournament = tournamentRepository.findById(tournamentId);
        return foundTournament.map(value -> new TournamentAdapter(value));
    }



    @Override
    public int getNumberOfTournaments() {
        return Math.toIntExact(tournamentRepository.count());
    }



    @Override
    public void createTournaments(Collection<ITournament> tournaments) {
        tournaments.forEach(tournament -> createTournament(tournament));        
    }

}
