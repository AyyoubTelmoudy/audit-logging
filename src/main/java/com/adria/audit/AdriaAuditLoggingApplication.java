package com.adria.audit;

import com.adria.audit.advice.AuditAdvice;
import com.adria.audit.advice.UserCredentials;
import com.adria.audit.event.EventSource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;



@SpringBootApplication()
public class AdriaAuditLoggingApplication {

	public static void main(String[] args)
	{
		SpringApplication.run(AdriaAuditLoggingApplication.class, args);
	}

	 @Bean
	 public AuditAdvice auditAdvice(UserCredentials authenticationUser, ApplicationEventPublisher applicationEventPublisher, HttpServletRequest httpServletRequest)
	 {
		 return new AuditAdvice(authenticationUser,applicationEventPublisher,httpServletRequest, EventSource.MSCONSULTATION);
	 }
}
