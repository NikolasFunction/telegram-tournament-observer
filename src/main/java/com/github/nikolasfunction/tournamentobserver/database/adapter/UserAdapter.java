package com.github.nikolasfunction.tournamentobserver.database.adapter;

import java.util.List;
import java.util.stream.Collectors;

import com.github.nikolasfunction.tournamentobserver.database.api.ITournament;
import com.github.nikolasfunction.tournamentobserver.database.api.IUser;
import com.github.nikolasfunction.tournamentobserver.database.entity.User;

public class UserAdapter implements IUser {
    
    private final User user;
    
    public UserAdapter(User user) {
        this.user = user;
    }

    @Override
    public int getId() {
        return user.getId();
    }

    @Override
    public List<ITournament> getObservedTournaments() {
        return user.getObservedTournaments().stream()
                .map(tournament -> new TournamentAdapter(tournament))
                .collect(Collectors.toList());
    }

    @Override
    public String getName() {
        return user.getName();
    }


}
