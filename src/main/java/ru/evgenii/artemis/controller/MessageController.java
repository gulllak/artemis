package ru.evgenii.artemis.controller;

import ch.qos.logback.classic.pattern.MessageConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.evgenii.artemis.model.Person;
import ru.evgenii.artemis.service.JmsSender;

@RestController
@RequestMapping("api/artemis")
@RequiredArgsConstructor
@Slf4j
public class MessageController {

    private final JmsSender jmsSender;

    @PostMapping("/send")
    public String sendMessage(@RequestParam("message") String message) {
        log.info("Пришло сообщение: {}", message);
        Person person = Person.builder()
                .firstName("Jenya")
                .lastName(message)
                .build();
        jmsSender.sendMessage(person);
        return "Message sent to in queue.";
    }
}
