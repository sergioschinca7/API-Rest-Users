package com.challenge.notificacion.infrastructure.controllers;

import com.challenge.notificacion.domain.Notificacion;
import com.challenge.notificacion.domain.NotificacionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GrettingController {

    @Autowired
    private NotificacionHandler notificacionHandler;
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Notificacion greet(Notificacion notificacion){



        return new Notificacion("Hello,"+ HtmlUtils.htmlEscape(notificacion.getMessage()));
    }






}
