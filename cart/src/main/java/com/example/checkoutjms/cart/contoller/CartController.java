package com.example.checkoutjms.cart.contoller;

import com.example.checkoutjms.cart.dto.OrderDto;
import com.example.checkoutjms.cart.jms.OrderSender;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {
    private final OrderSender orderSender;

    public CartController(OrderSender orderSender) {
        this.orderSender = orderSender;
    }

    @PostMapping("/saveOrder")
    public void saveOrder(@RequestBody OrderDto order) {
        if (order == null) {
            throw new NullPointerException("The order not found!");
        }
        orderSender.send(order);
    }
}
