package com.github.nikolasfunction.tournamentobserver.web;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import com.github.nikolasfunction.tournamentobserver.exception.ConnectionException;

@Component
public class HtmlRequester implements IHtmlRequester {

    public Document requestHtml(String url) throws ConnectionException {
        Document doc;
        try {
                doc = Jsoup.connect(url).get();
        } catch (IOException e) {
                throw new ConnectionException(e);
        }
        return doc;
    }

}
