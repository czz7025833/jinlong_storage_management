package com.jinlong.management.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "SKU is mandatory")
    private String sku;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotNull(message = "Size is mandatory")
    private int size;

    @NotNull(message = "price is mandatory")
    private int standardPrice;

    @NotNull(message = "Color is mandatory")
    private String color;

    @NotNull(message = "Quantity is mandatory")
    private int quantity;

}
