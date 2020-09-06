package com.github.nikolasfunction.tournamentobserver.scanner;

import java.util.List;

import com.github.nikolasfunction.tournamentobserver.database.api.ITournament;

public interface IClickTtScanner {
    
    public List<ITournament> scanClickTt();

}
