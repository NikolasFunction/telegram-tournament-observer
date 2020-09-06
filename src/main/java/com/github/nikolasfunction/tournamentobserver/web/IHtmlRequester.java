package com.github.nikolasfunction.tournamentobserver.web;

import org.jsoup.nodes.Document;

import com.github.nikolasfunction.tournamentobserver.exception.ConnectionException;

public interface IHtmlRequester {
    
    public Document requestHtml(String url) throws ConnectionException;

}
