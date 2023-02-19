package com.github.nikolasfunction.tournamentobserver.database.api.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.github.nikolasfunction.tournamentobserver.database.api.IUser;

public interface IUserService {
    
    public void createUser(IUser user);
    
    public void creteUsers(Collection<IUser> users);
    
    public List<IUser> getUsers();
    
    public int getNumberOfUser();
    
    public Optional<IUser> getUser(int userId);

}
