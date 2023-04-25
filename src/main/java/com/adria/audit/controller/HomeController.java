package com.adria.audit.controller;

import com.adria.audit.annotation.AuditedClass;
import com.adria.audit.annotation.AuditedMethod;
import com.adria.audit.event.EventRaison;
import com.adria.audit.event.ActionType;
import com.adria.audit.event.TypeEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping
@AuditedClass
public class HomeController {
    @GetMapping("/hello/{name}")
    @AuditedMethod(raisonEvent = EventRaison.DemandePaiement,typeEvent = TypeEvent.DebitEvent,typeAction = ActionType.CREATION)
    public Map<String,String> hello(@PathVariable String name)
    {
        Map<String,String> map=new HashMap<>();
        map.put("name","ayyoub");
        map.put("age","35");
        return map;
    }
}
