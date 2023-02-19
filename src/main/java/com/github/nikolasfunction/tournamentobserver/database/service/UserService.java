package com.github.nikolasfunction.tournamentobserver.database.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.nikolasfunction.tournamentobserver.database.adapter.UserAdapter;
import com.github.nikolasfunction.tournamentobserver.database.api.IUser;
import com.github.nikolasfunction.tournamentobserver.database.api.service.IUserService;
import com.github.nikolasfunction.tournamentobserver.database.entity.Tournament;
import com.github.nikolasfunction.tournamentobserver.database.entity.User;
import com.github.nikolasfunction.tournamentobserver.database.repository.IUserRepository;

@Service
public class UserService implements IUserService {
    
    private IUserRepository userRepository;
    
    @Autowired
    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public void createUser(IUser user) {
        
        User userEntity = new User();
        userEntity.setId(user.getId());
        userEntity.setName(user.getName());
        userEntity.setObservedTournaments(user.getObservedTournaments().stream()
                .map(tournament -> new Tournament(tournament.getId(), tournament.getTime(), tournament.getUrl(), tournament.getParticipants(), tournament.getFreePlaces()))
                .collect(Collectors.toSet()));
        
        userRepository.saveAndFlush(userEntity);
        
    }


    @Override
    public List<IUser> getUsers() {
        
        return userRepository.findAll().stream()
                .map(user -> new UserAdapter(user))
                .collect(Collectors.toList());
    }


    @Override
    public Optional<IUser> getUser(int userId) {
        return userRepository.findById(userId).map(value -> new UserAdapter(value));
    }


    @Override
    public int getNumberOfUser() {
        return Math.toIntExact(userRepository.count());
    }


    @Override
    public void creteUsers(Collection<IUser> users) {
        users.forEach(user -> createUser(user));
    }

}
