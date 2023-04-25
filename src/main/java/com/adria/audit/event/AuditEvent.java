package com.adria.audit.event;

import org.springframework.context.ApplicationEvent;

public class AuditEvent extends  ApplicationEvent {

    private final Event event;

    public AuditEvent(Object source, Event event) {
        super(source);
        this.event=event;
    }

    public Event getEvent() {
        return this.event;
    }
}