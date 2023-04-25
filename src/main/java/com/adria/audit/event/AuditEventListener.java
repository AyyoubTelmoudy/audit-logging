package com.adria.audit.event;


import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class AuditEventListener implements ApplicationListener<AuditEvent> {
        @Override
        public void onApplicationEvent(AuditEvent auditEvent) {

                System.out.println(auditEvent.getEvent().toString());
        }
}