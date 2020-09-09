package com.github.nikolasfunction.tournamentobserver.telegram;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.abilitybots.api.objects.Ability;
import org.telegram.abilitybots.api.objects.Locality;
import org.telegram.abilitybots.api.objects.Privacy;

@Component
public class TournamentObserverBot extends AbilityBot  {
    
    private final int chatId;
    
    public TournamentObserverBot(
            @Value("${telegram.apitoken}") String botToken,
            @Value("${telegram.developer.chatid}") int chatId) {
        super(botToken, "SomeUsername");
        this.chatId = chatId;
    }

    @Override
    public int creatorId() {
        return chatId;
    }
    
    public Ability sayHelloWorld() {
        return Ability
                  .builder()
                  .name("hello")
                  .info("says hello world!")
                  .locality(Locality.ALL)
                  .privacy(Privacy.PUBLIC)
                  .action(ctx -> silent.send("Hello world!", ctx.chatId()))
                  .build();
    }
    
}
