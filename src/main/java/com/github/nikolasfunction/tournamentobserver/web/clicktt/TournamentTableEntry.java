package com.github.nikolasfunction.tournamentobserver.web.clicktt;

import java.util.Date;

public class TournamentTableEntry implements ITournamentTableEntry {
    
    private Date time;
    private String url;
    private String organizer;
    private int freePlaces;
    private int participants;
    private int queueSize;
    private String region;
    private String openFor;
    private String ageGroup;    
    private String pdfUrl;
    
    public TournamentTableEntry(Date time, String url, String organizer,
            int freePlaces, int participants, int queueSize, String region,
            String openFor, String ageGroup, String pdfUrl) {
        super();
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
