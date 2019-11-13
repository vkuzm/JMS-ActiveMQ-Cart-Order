package com.example.checkoutjms.order.contoller;

import com.example.checkoutjms.order.domain.Order;
import com.example.checkoutjms.order.dto.OrderDto;
import com.example.checkoutjms.order.repository.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderRepository orderRepo;
    private final ModelMapper modelMapper;

    public OrderController(OrderRepository orderRepo, ModelMapper modelMapper) {
        this.orderRepo = orderRepo;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<OrderDto> findAll() {
        List<Order> orders = orderRepo.findAll();

        return orders.stream()
                .map(o -> modelMapper.map(o, OrderDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public OrderDto findOne(@PathVariable("id") Long id) {
        Optional<Order> order = orderRepo.findById(id);

        return order.map(o -> modelMapper.map(o, OrderDto.class))
                .orElse(null);
    }
}
