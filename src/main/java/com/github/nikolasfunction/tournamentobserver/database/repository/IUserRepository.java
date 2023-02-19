package com.github.nikolasfunction.tournamentobserver.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.nikolasfunction.tournamentobserver.database.entity.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {

}
