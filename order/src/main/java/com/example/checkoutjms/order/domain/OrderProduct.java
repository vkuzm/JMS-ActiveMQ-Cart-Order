package com.example.checkoutjms.order.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "order_products")
@Data
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long productId;
    private String name;
    private int quantity;
    private int price;
}
