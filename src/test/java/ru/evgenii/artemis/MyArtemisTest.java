package ru.evgenii.artemis;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.TextMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import ru.evgenii.artemis.model.Person;

import static org.assertj.core.api.Assertions.assertThat;

class MyArtemisTest extends ArtemisAbstractTest {

    private static final String TEST_QUEUE = "my_queue";

    @Autowired
    private JmsTemplate jmsTemplate;

    @Test
    void sendMessage() throws JMSException {
        Person person = new Person("Boris", "Britva");
        jmsTemplate.convertAndSend(TEST_QUEUE, person);
        Message message = jmsTemplate.receive(TEST_QUEUE);
        TextMessage textMessage = (TextMessage) message;
        assert textMessage != null;
        assertThat(textMessage.getText())
                .isEqualTo("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><person><firstName>Boris</firstName><lastName>Britva</lastName></person>");
    }
}
