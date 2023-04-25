package com.adria.audit.event;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level=AccessLevel.PRIVATE)
public class Event {
	List<Attribute> flux;
	ActionType typeAction;
	String username;
	String inetAddress;
	Date date;
	EventRaison raisonEvent;
	TypeEvent typeEvent;
	EventStatus statusEvent;
	EventSource sourceEvent;
}
