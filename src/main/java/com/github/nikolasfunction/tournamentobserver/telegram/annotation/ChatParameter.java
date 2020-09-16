package com.github.nikolasfunction.tournamentobserver.telegram.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.telegram.telegrambots.meta.api.objects.Chat;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Parameter({Chat.class})
public @interface ChatParameter {

}
