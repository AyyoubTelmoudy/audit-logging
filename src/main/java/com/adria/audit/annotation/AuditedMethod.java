package com.adria.audit.annotation;


import com.adria.audit.event.EventRaison;
import com.adria.audit.event.ActionType;
import com.adria.audit.event.TypeEvent;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AuditedMethod {
    ActionType typeAction();
    EventRaison raisonEvent();
    TypeEvent typeEvent();
}
