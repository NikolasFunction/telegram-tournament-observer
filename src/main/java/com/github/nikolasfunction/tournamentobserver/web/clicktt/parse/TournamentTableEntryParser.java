package com.github.nikolasfunction.tournamentobserver.web.clicktt.parse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.nikolasfunction.tournamentobserver.web.clicktt.ITournamentTableEntry;
import com.github.nikolasfunction.tournamentobserver.web.clicktt.factory.ITournamentTableEntryFactory;

@Component
public class TournamentTableEntryParser implements ITournamentTableEntryParser {
    
    private static final String HOST = "https://bttv.click-tt.de";
    
    private static final int POSITION_DATETIME = 0;
    private static final int POSITION_URL = 1;
    private static final int POSITION_ORGANIZER = 1;
    private static final int POSITION_PARTICIPANTS = 2;
    private static final int POSITION_QUEUE = 3;
    private static final int POSITION_REGION = 4;
    private static final int POSITION_OPENFOR = 5;
    private static final int POSITION_AGEGROUP = 6;
    private static final int POSITION_PDF = 7;
    
    private final ITournamentTableEntryFactory tournamentTableEntryFactory;
    
    @Autowired
    public TournamentTableEntryParser(
            ITournamentTableEntryFactory tournamentTableEntryFactory) {
        this.tournamentTableEntryFactory = tournamentTableEntryFactory;
    }

    @Override
    public ITournamentTableEntry parseTournamentTableEntry(Element element) throws com.github.nikolasfunction.tournamentobserver.exception.ParseException {
        
        String dateString = element.child(POSITION_DATETIME).text();
        Date time = dateTimeConverter(dateString);
        
        StringBuilder urlString = new StringBuilder(HOST)
                .append(element.child(POSITION_URL).select("a[href]").attr("href"));
        String url = urlString.toString().replace("amp;", "");        
        
        String organizerString = element.child(POSITION_ORGANIZER).text();
        String organizer = organizerString.substring("BTTV Bavarian TT-Race ".length());
        
        String participantsString = element.child(POSITION_PARTICIPANTS).text();
        String[] participantsSplitted = participantsString.split("/");
        int freePlaces;
        int participants;
        try {
            freePlaces = Integer.parseInt(participantsSplitted[0]);
            participants = Integer.parseInt(participantsSplitted[1]);
        }
        catch(ArrayIndexOutOfBoundsException e) {
            throw new com.github.nikolasfunction.tournamentobserver.exception.ParseException(e);
        }
        
        String queueSizeString = element.child(POSITION_QUEUE).text();
        int queueSize = 0;
        try {
            queueSize = Integer.parseInt(queueSizeString);
        }
        catch(NumberFormatException e) {}
        
        String region = element.child(POSITION_REGION).text();
        
        String openFor = element.child(POSITION_OPENFOR).text();
        
        String ageGroup = element.child(POSITION_AGEGROUP).text();
        
        StringBuilder pdfUrlString = new StringBuilder(HOST)
                .append(element.child(POSITION_PDF).select("a[href]").attr("href"));
        String pdfUrl = pdfUrlString.toString().replace("amp;", "");
        
        return tournamentTableEntryFactory.createTournamentTableEntry(time, url, organizer, freePlaces, participants, queueSize, region, openFor, ageGroup, pdfUrl);
        
    }
    
    private Date dateTimeConverter(String dateTimeString) throws com.github.nikolasfunction.tournamentobserver.exception.ParseException {
        
        dateTimeString = dateTimeString.substring("Mo. ".length());
        
        Date dateTime;
        try {
            dateTime = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.GERMAN).parse(dateTimeString);
        } catch (ParseException e) {
            throw new com.github.nikolasfunction.tournamentobserver.exception.ParseException(e);
        }
        
        return dateTime;
        
        
    }

}
