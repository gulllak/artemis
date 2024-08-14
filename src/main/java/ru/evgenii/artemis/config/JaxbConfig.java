package ru.evgenii.artemis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MarshallingMessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class JaxbConfig {

    @Bean
    public Jaxb2Marshaller jaxb2Marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setPackagesToScan("ru.evgenii.artemis.model");
        return marshaller;
    }

    @Bean
    MessageConverter messageConverter(Marshaller marshaller) {
        MarshallingMessageConverter converter = new MarshallingMessageConverter(marshaller);
        converter.setTargetType(MessageType.TEXT);
        return converter;
    }

    //	@Bean
//	MessageConverter messageConverter() {
//		ObjectMapper objectMapper = new ObjectMapper();
//		objectMapper.registerModule(new JavaTimeModule());
//		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
//		converter.setObjectMapper(objectMapper);
//		converter.setTargetType(MessageType.TEXT);
//		converter.setTypeIdPropertyName("_type");
//		HashMap<String, Class<?>> typeIdMappings = new HashMap<>();
//		typeIdMappings.put("person", Person.class);
//		converter.setTypeIdMappings(typeIdMappings);
//		return converter;
//	}
}