package com.github.nikolasfunction.tournamentobserver.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.nikolasfunction.tournamentobserver.database.entity.Tournament;

@Repository
public interface ITournamentRepository extends JpaRepository<Tournament, Long> {

}
