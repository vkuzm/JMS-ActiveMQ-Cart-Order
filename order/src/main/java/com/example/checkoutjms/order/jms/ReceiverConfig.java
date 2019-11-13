package com.example.checkoutjms.order.jms;

import com.example.checkoutjms.order.dto.OrderDto;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import javax.jms.ConnectionFactory;
import java.util.HashMap;

@Configuration
@EnableJms
public class ReceiverConfig {
    @Autowired
    private Environment env;

    @Bean
    public ActiveMQConnectionFactory receiverActiveMQConnectionFactory() {
        String brokerUrl = env.getProperty("spring.activemq.broker-url");

        ActiveMQConnectionFactory activeMQConnectionFactory =
                new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL(brokerUrl);

        return activeMQConnectionFactory;
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
    public JmsListenerContainerFactory<?> jsaFactory(
            ConnectionFactory connectionFactory,
            DefaultJmsListenerContainerFactoryConfigurer configurer) {

        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setMessageConverter(jacksonJmsMessageConverter());
        configurer.configure(factory, connectionFactory);
        return factory;
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory
                = new DefaultJmsListenerContainerFactory();

        factory.setConnectionFactory(receiverActiveMQConnectionFactory());
        return factory;
    }
}
