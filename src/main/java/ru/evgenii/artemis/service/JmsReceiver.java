package ru.evgenii.artemis.service;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class JmsReceiver {

    @JmsListener(destination = "queue")
    public void receiveMessage(String message) {
        System.out.println("Received message from out queue: " + message);
    }
}
