package com.example.checkoutjms.cart.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class,
        property = "@id",
        scope = OrderProductDto.class)
@Data
public class OrderProductDto {
    private long productId;
    private String name;
    private int quantity;
    private int price;
}
