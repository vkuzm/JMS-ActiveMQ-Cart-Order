package com.example.checkoutjms.cart.jms;

import com.example.checkoutjms.cart.dto.OrderDto;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import java.util.HashMap;

@Configuration
public class SenderConfig {
    @Autowired
    private Environment env;

    @Bean
    public ActiveMQConnectionFactory senderActiveMQConnectionFactory() {
        String brokerUrl = env.getProperty("spring.activemq.broker-url");

        ActiveMQConnectionFactory activeMQConnectionFactory =
                new ActiveMQConnectionFactory();

        activeMQConnectionFactory.setBrokerURL(brokerUrl);

        return activeMQConnectionFactory;
    }

    @Bean
    public CachingConnectionFactory cachingConnectionFactory() {
        return new CachingConnectionFactory(
                senderActiveMQConnectionFactory());
    }

    @Bean
    public MessageConverter jacksonJmsMessageConverter() {
            MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        HashMap<String, Class<?>> typeIdMappings = new HashMap<>();
        typeIdMappings.put(OrderDto.class.getSimpleName(), OrderDto.class);
        converter.setTypeIdMappings(typeIdMappings);
        converter.setTypeIdPropertyName("_type");

        return converter;
    }

    @Bean
    public JmsTemplate jmsTemplate() {
        JmsTemplate template = new JmsTemplate();
        template.setMessageConverter(jacksonJmsMessageConverter());
        template.setConnectionFactory(cachingConnectionFactory());
        return template;
    }
}
