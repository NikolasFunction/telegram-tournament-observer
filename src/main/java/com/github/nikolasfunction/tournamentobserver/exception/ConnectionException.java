package com.github.nikolasfunction.tournamentobserver.exception;

public class ConnectionException extends Exception {
    
    /**
     * 
     */
    private static final long serialVersionUID = 5045916029881491781L;

    public ConnectionException() {
        super();
    }
    
    public ConnectionException(String message) {
        super(message);
    }
    
    public ConnectionException(Throwable throwable) {
        super(throwable);
    }
    
    public ConnectionException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
