package ru.evgenii.artemis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import ru.evgenii.artemis.model.Person;

import java.util.HashMap;

@SpringBootApplication
public class ArtemisApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArtemisApplication.class, args);
	}

	@Bean
	MessageConverter messageConverter() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setObjectMapper(objectMapper);
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("_type");
		HashMap<String, Class<?>> typeIdMappings = new HashMap<>();
		typeIdMappings.put("person", Person.class);
		converter.setTypeIdMappings(typeIdMappings);
		return converter;
	}
}
