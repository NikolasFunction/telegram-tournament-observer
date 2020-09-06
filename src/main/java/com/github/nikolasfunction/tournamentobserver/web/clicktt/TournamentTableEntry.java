package com.github.nikolasfunction.tournamentobserver.web.clicktt;

import java.util.Date;

public class TournamentTableEntry implements ITournamentTableEntry {
    
    private final long tournamentId;
    private final Date time;
    private final String url;
    private final String organizer;
    private final int freePlaces;
    private final int participants;
    private final int queueSize;
    private final String region;
    private final String openFor;
    private final String ageGroup;    
    private final String pdfUrl;
    
    public TournamentTableEntry(long tournamentId, Date time, String url, String organizer,
            int freePlaces, int participants, int queueSize, String region,
            String openFor, String ageGroup, String pdfUrl) {
        this.tournamentId = tournamentId;
        this.time = time;
        this.url = url;
        this.organizer = organizer;
        this.freePlaces = freePlaces;
        this.participants = participants;
        this.queueSize = queueSize;
        this.region = region;
        this.openFor = openFor;
        this.ageGroup = ageGroup;
        this.pdfUrl = pdfUrl;
    }
    
    @Override
    public long getTournamentId() {
        return tournamentId;
    }

    @Override
    public Date getTime() {
        return time;
    }
    
    @Override
    public String getUrl() {
        return url;
    }
    
    @Override
    public String getOrganizer() {
        return organizer;
    }
    
    @Override
    public int getFreePlaces() {
        return freePlaces;
    }
    
    @Override
    public int getParticipants() {
        return participants;
    }
    
    @Override
    public int getQueueSize() {
        return queueSize;
    }
    
    @Override
    public String getRegion() {
        return region;
    }
    
    @Override
    public String getOpenFor() {
        return openFor;
    }
    
    @Override
    public String getAgeGroup() {
        return ageGroup;
    }
    
    @Override
    public String getPdfUrl() {
        return pdfUrl;
    }
    
}
