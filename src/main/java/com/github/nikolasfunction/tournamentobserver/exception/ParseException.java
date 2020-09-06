package com.github.nikolasfunction.tournamentobserver.exception;

public class ParseException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 3653089487709255869L;

    public ParseException() {
        super();
    }
    
    public ParseException(String message) {
        super(message);
    }
    
    public ParseException(Throwable throwable) {
        super(throwable);
    }
    
    public ParseException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
