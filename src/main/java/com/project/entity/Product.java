package com.project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "tbl_product")
public class Product {

    @Id
    @Column(name = "p_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;

    @Column(name = "title")
    @NotEmpty(message = "invalid title")
    private String title;

    @Column(name="description")
    @NotEmpty(message = "invalid description")
    private String description;

    @Column(name = "mrp")
    @Min(value = 1,message = "mrp must 1 or greater then 1")
    private int mrp;

    @Column(name = "discount")
    @Max(value = 99,message = "discount must be smaller then 99")
    private double discount;

    @Column(name="current_price")
    @Min(1)
    private int currentPrice;

    @Column(name = "inventory_count")
    @Min(1)
    private int inventoryCount;

    @Column(name="gst")
    @Min(1)
    private double gst;

    @OneToMany(mappedBy = "product",fetch = FetchType.EAGER)
    private List<Order> orderList;

    @OneToMany(mappedBy = "product")
    private List<Bill> bills;
}
