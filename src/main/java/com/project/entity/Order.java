package com.project.entity;

import com.project.entity.embeddable.Address;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name = "tbl_order")
public class Order {

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int  orderId;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @NotNull(message = "customer can not be null")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @NotNull(message = "product can not be null")
    private Product product;

    @Min(value = 1,message = "quantity must greater then 1 or 1")
    private int quantity;


    @NotNull(message = "address can not be null")
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "pinCode",column = @Column(name = "pin_code")),
            @AttributeOverride(name = "city",column = @Column(name = "city")),
            @AttributeOverride(name = "state",column = @Column(name = "state")),
            @AttributeOverride(name = "apartmentOrSociety",column = @Column(name = "apartment_or_society")),
            @AttributeOverride(name = "apartmentOrSocietyNumber",column = @Column(name = "no"))
    })
    private Address address;

    @OneToOne(mappedBy = "order",cascade = CascadeType.ALL)
    private Bill bill;
}
