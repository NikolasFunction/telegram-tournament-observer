package com.github.nikolasfunction.tournamentobserver.telegram.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Tags a method in order to be called when receiving a command.
 * Valid return values: void, subtype of BaseRequest
 * Valid parameters: String, Integer
 * @author Nikolas Paripovic
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BotCommand {
    
    String value();
    String description();
    boolean hidden() default false;

}
