package com.github.nikolasfunction.tournamentobserver.scanner;

import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.nikolasfunction.tournamentobserver.database.api.ITournament;
import com.github.nikolasfunction.tournamentobserver.exception.ConnectionException;
import com.github.nikolasfunction.tournamentobserver.exception.ParseException;
import com.github.nikolasfunction.tournamentobserver.web.IHtmlRequester;
import com.github.nikolasfunction.tournamentobserver.web.clicktt.ITournamentTable;
import com.github.nikolasfunction.tournamentobserver.web.clicktt.ITournamentTableUrlGenerator;
import com.github.nikolasfunction.tournamentobserver.web.clicktt.parse.ITournamentTableParser;

@Component
public class ClickTtScanner implements IClickTtScanner {

    private final ITournamentTableUrlGenerator tournamentTableUrlGenerator;

    private final IHtmlRequester htmlRequester;

    private final ITournamentTableParser tournamentTableParser;
    

    @Autowired
    public ClickTtScanner(
            ITournamentTableUrlGenerator tournamentTableUrlGenerator,
            IHtmlRequester htmlRequester,
            ITournamentTableParser tournamentTableParser) {
        this.tournamentTableUrlGenerator = tournamentTableUrlGenerator;
        this.htmlRequester = htmlRequester;
        this.tournamentTableParser = tournamentTableParser;
    }


    @Override
    public List<ITournament> scanClickTt() {
        List<LocalDate> monthsToCheck = new ArrayList<>();

        LocalDate thisMonth = LocalDate.now().withDayOfMonth(1);
        LocalDate nextMonth = thisMonth.plusMonths(1);
        LocalDate thirdMonth = nextMonth.plusMonths(1);

        monthsToCheck.add(thisMonth);
        monthsToCheck.add(nextMonth);
        monthsToCheck.add(thirdMonth);
        
        List<ITournament> tournaments = new ArrayList<>();

        for (LocalDate date : monthsToCheck) {

            String url = tournamentTableUrlGenerator.generateTournamentTableUrl(
                    date.getMonth(), Year.of(date.getYear()));

            Document html = null;
            try {
                html = htmlRequester.requestHtml(url);
            } catch (ConnectionException e) {
                e.printStackTrace();
            }

            ITournamentTable table = null;
            try {
                table = tournamentTableParser
                        .parseTournamentTable(html);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            
            tournaments.addAll(table.getTournamentTableEntries().stream().map(tableEntry -> new TournamentAdapter(tableEntry)).collect(Collectors.toList()));
            
        }
        return tournaments;
    }

}
