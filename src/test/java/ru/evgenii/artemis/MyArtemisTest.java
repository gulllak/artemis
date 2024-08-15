package ru.evgenii.artemis;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.TextMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import ru.evgenii.artemis.model.Person;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MyArtemisTest extends ArtemisAbstractTest {

    private static final String TEST_QUEUE = "my_queue";

    @Autowired
    private JmsTemplate jmsTemplate;

    @Test
    void sendMessage() throws JMSException {

        Person person = new Person("Boris", "Britva");
        String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><person><firstName>Boris</firstName><lastName>Britva</lastName></person>";

        jmsTemplate.convertAndSend(TEST_QUEUE, person);
        Message message = jmsTemplate.receive(TEST_QUEUE);

        assertNotNull(message);
        assertThat(((TextMessage) message).getText())
                .isEqualTo(expected);
    }
}
