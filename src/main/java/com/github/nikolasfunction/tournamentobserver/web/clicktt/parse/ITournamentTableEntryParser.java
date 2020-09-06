package com.github.nikolasfunction.tournamentobserver.web.clicktt.parse;

import org.jsoup.nodes.Element;

import com.github.nikolasfunction.tournamentobserver.exception.ParseException;
import com.github.nikolasfunction.tournamentobserver.web.clicktt.ITournamentTableEntry;

public interface ITournamentTableEntryParser {
    
    ITournamentTableEntry parseTournamentTableEntry(Element element) throws ParseException;

}
