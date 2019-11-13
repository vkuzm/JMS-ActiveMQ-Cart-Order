package com.example.checkoutjms.cart.jms;

import com.example.checkoutjms.cart.dto.OrderDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderSender {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderSender.class);
    private final JmsTemplate jmsTemplate;

    @Autowired
    public OrderSender(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void send(OrderDto order) {
        LOGGER.info("sending order='{}'", order);
        jmsTemplate.convertAndSend("order", order);
    }
}
