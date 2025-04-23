package com.project.entity;

import com.project.entity.embeddable.Address;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name = "tbl_bill")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bill_id")
    private int billId;

    @NotNull(message = "product can not be null")
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @NotNull(message = "customer can not be null")
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

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

    @Column(name = "total-amount-payable")
    private double totalAmountPayAble;

    @Min(value = 1,message = "quantity must greater then 1")
    private int quantity;

    @OneToOne
    @NotNull
    @JoinColumn(name = "order_id")
    private Order order;
}
