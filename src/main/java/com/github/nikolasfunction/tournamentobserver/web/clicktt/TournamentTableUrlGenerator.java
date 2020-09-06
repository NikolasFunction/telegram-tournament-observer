package com.github.nikolasfunction.tournamentobserver.web.clicktt;

import java.time.Month;
import java.time.Year;

import org.springframework.stereotype.Component;

@Component
public class TournamentTableUrlGenerator implements ITournamentTableUrlGenerator {
    
    @Override
    public String generateTournamentTableUrl(Month month, Year year) {
        return String.format("https://bttv.click-tt.de/cgi-bin/WebObjects/nuLigaTTDE.woa/wa/tournamentCalendar?circuit=%d_BTTR&federation=ByTTV&date=%d-%02d-01", year.getValue(), year.getValue(), month.getValue());
    }

}
