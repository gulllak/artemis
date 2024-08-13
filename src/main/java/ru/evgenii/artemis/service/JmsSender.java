package ru.evgenii.artemis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import ru.evgenii.artemis.model.Person;

@Service
@RequiredArgsConstructor
public class JmsSender {

    private final JmsTemplate jmsTemplate;

    public void sendMessage(Person person) {
        jmsTemplate.convertAndSend("queue", person);
    }
}