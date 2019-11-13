package com.example.checkoutjms.order.jms;

import com.example.checkoutjms.order.domain.Order;
import com.example.checkoutjms.order.dto.OrderDto;
import com.example.checkoutjms.order.repository.OrderRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class OrderReceiver {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(OrderReceiver.class);

    private final OrderRepository orderRepo;
    private final ModelMapper modelMapper;

    public OrderReceiver(OrderRepository orderRepo, ModelMapper modelMapper) {
        this.orderRepo = orderRepo;
        this.modelMapper = modelMapper;
    }

    @JmsListener(destination = "order", containerFactory="jsaFactory")
    public void receive(OrderDto orderDto) {
        if (orderDto != null) {
            LOGGER.info("received order='{}'", orderDto);

            Order order = modelMapper.map(orderDto, Order.class);
            orderRepo.save(order);
        }
    }
}
