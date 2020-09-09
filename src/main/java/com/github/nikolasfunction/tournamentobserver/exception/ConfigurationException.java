package com.github.nikolasfunction.tournamentobserver.exception;

public class ConfigurationException extends Exception {
    
    /**
     * 
     */
    private static final long serialVersionUID = -3675188846972478318L;

    public ConfigurationException() {
        super();
    }
    
    public ConfigurationException(String message) {
        super(message);
    }
    
    public ConfigurationException(Throwable throwable) {
        super(throwable);
    }
    
    public ConfigurationException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
