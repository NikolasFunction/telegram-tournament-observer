package com.github.nikolasfunction.tournamentobserver.web.clicktt.parse;

import org.jsoup.nodes.Document;

import com.github.nikolasfunction.tournamentobserver.exception.ParseException;
import com.github.nikolasfunction.tournamentobserver.web.clicktt.ITournamentTable;

public interface ITournamentTableParser {
    
    public ITournamentTable parseTournamentTable(Document html) throws ParseException;

}
