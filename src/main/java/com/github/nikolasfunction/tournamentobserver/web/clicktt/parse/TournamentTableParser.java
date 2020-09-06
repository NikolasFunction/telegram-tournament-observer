package com.github.nikolasfunction.tournamentobserver.web.clicktt.parse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.github.nikolasfunction.tournamentobserver.web.clicktt.ITournamentTable;
import com.github.nikolasfunction.tournamentobserver.web.clicktt.ITournamentTableEntry;
import com.github.nikolasfunction.tournamentobserver.web.clicktt.factory.ITournamentTableFactory;
import com.github.nikolasfunction.tournamentobserver.web.clicktt.factory.TournamentTableFactory;

public class TournamentTableParser implements ITournamentTableParser {
    
    private final ITournamentTableFactory tournamentTableFactory = new TournamentTableFactory();
    
    private final ITournamentTableEntryParser tournamentTableEntryParser = new TournamentTableEntryParser();

    @Override
    public ITournamentTable parseTournamentTable(Document html) throws com.github.nikolasfunction.tournamentobserver.exception.ParseException {
        
        Element urlElement = html.select("meta[name=nuLigaStatsUrl][content]").first();
        String urlElementContent = urlElement.attr("content");
        
        int dateBeginPosition = urlElementContent.indexOf("date=") + "date=".length();
        String dateString = urlElementContent.substring(dateBeginPosition, dateBeginPosition + "2020-01-01".length());
        
        Date date;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd", Locale.GERMAN).parse(dateString);
        } catch (ParseException e) {
            throw new com.github.nikolasfunction.tournamentobserver.exception.ParseException(e);
        }
        
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        Year year = Year.of(calendar.get(Calendar.YEAR));
        Month month = Month.of(calendar.get(Calendar.MONTH) + 1);
        
        
        List<Element> elements = html.select("body > div > div#container > div#content > div#content-row2 > table > tbody > tr");
        elements = elements.subList(2, elements.size());
        
        List<ITournamentTableEntry> tournamentTableEntries = new ArrayList<>();
        
        for(Element element : elements) {
            tournamentTableEntries.add(tournamentTableEntryParser.parseTournamentTableEntry(element));
        }
        
        
        return tournamentTableFactory.createTournamentTable(month, year, tournamentTableEntries);        
    }
    
    

}
