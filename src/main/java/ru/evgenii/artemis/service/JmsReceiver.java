package ru.evgenii.artemis.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import ru.evgenii.artemis.model.Person;

@Component
@Slf4j
@RequiredArgsConstructor
public class JmsReceiver {

    @JmsListener(destination = "queue")
    public void receiveMessage(Person person) {
        System.out.println("Received message from out queue: " + person.toString());
    }
}
