package com.example.checkoutjms.cart.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class,
        property = "@id",
        scope = OrderDto.class)
@Data
public class OrderDto {
    private long id;
    private String firstName;
    private String lastName;
    private String address;
    private List<OrderProductDto> products = new ArrayList<>();
}
