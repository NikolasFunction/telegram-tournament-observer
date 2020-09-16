package com.github.nikolasfunction.tournamentobserver.exception;

public class TelegramException extends Exception {
    
    /**
     * 
     */
    private static final long serialVersionUID = 4869891112178599799L;

    public TelegramException() {
        super();
    }
    
    public TelegramException(String message) {
        super(message);
    }
    
    public TelegramException(Throwable throwable) {
        super(throwable);
    }
    
    public TelegramException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
