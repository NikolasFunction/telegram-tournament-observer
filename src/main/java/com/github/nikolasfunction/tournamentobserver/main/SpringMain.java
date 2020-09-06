package com.github.nikolasfunction.tournamentobserver.main;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;

import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.github.nikolasfunction.tournamentobserver.web.IHtmlRequester;
import com.github.nikolasfunction.tournamentobserver.web.clicktt.ITournamentTable;
import com.github.nikolasfunction.tournamentobserver.web.clicktt.ITournamentTableUrlGenerator;
import com.github.nikolasfunction.tournamentobserver.web.clicktt.parse.ITournamentTableParser;

@Component
public class SpringMain implements CommandLineRunner {
    
    private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
    
    private final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm");
    
    private final ITournamentTableParser tournamentTableParser;
    
    private final IHtmlRequester htmlRequester;
    
    private final ITournamentTableUrlGenerator tournamentTableUrlGenerator;
    
    @Autowired
    public SpringMain(ITournamentTableParser tournamentTableParser,
            IHtmlRequester htmlRequester,
            ITournamentTableUrlGenerator tournamentTableUrlGenerator) {
        this.tournamentTableParser = tournamentTableParser;
        this.htmlRequester = htmlRequester;
        this.tournamentTableUrlGenerator = tournamentTableUrlGenerator;
    }


    @Override
    public void run(String... args) throws Exception {
        
      LocalDate date = LocalDate.now();        
      String url = tournamentTableUrlGenerator.generateTournamentTableUrl(date.getMonth(), Year.now());
      
      Document html = htmlRequester.requestHtml(url);
      ITournamentTable table = tournamentTableParser.parseTournamentTable(html);
      
      System.out.println(String.format("Found %d entries in %s %d:", table.getTournamentTableEntries().size(), table.getMonth().toString(), table.getYear().getValue()));
      table.getTournamentTableEntries().stream().forEach(entry -> System.out.println(String.format("%s um %s in %s", DATE_FORMAT.format(entry.getTime()), TIME_FORMAT.format(entry.getTime()), entry.getOrganizer())));
      
    }
    
    

}
