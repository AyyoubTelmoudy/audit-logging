package com.adria.audit.advice;



import com.adria.audit.annotation.AuditedMethod;
import com.adria.audit.event.*;
import com.adria.audit.ustil.Utils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.ApplicationEventPublisher;


import java.lang.reflect.Method;
import java.util.Date;


@Aspect
@AllArgsConstructor
public class AuditAdvice {
	private UserCredentials userCredentials;
	private ApplicationEventPublisher eventPublisher;
	private HttpServletRequest request;
	private EventSource eventSource;
	@AfterReturning(
			pointcut = "@annotation(com.adria.audit.annotation.AuditedMethod) && @within(com.adria.audit.annotation.AuditedClass)",
			returning = "result"
	)
	public void onAuditEvent(JoinPoint joinPoint, Object result) throws IllegalAccessException {

		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		AuditedMethod auditedMethod= method.getAnnotation(AuditedMethod.class);

		Event event=Event.builder()
				.flux(Utils.getInputOutputFlux(joinPoint.getArgs(),result))
				.typeAction(auditedMethod.typeAction())
				.username(userCredentials.getUsername())
				.inetAddress(request.getRemoteAddr())
				.date(new Date())
				.raisonEvent(auditedMethod.raisonEvent())
				.typeEvent( auditedMethod.typeEvent())
				.statusEvent(EventStatus.SUCCESS)
				.sourceEvent(eventSource)
				.build();
		eventPublisher.publishEvent(new AuditEvent(this, event));
	}
}
