package com.project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import java.util.List;

@Entity
@Data
@Table(name = "tbl_customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="customer_id")
    private int customerId;

    @Column(name = "customer_name")
    @NotBlank(message = "invalid customer name")
    private String customerName;

    @Column(name="phone_number",unique = true)
    @Pattern(regexp = "^[1-9][0-9]{9}$",message = "invalid phone number")
    @NotBlank(message = "phoneNumber customer name")
    private String phoneNumber;

    @Column(name="email",unique = true)
    @Pattern(regexp = "^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-z A-Z]{2,3}$",message = "invalid email")
    private String email;

    @OneToMany(mappedBy = "customer",fetch = FetchType.EAGER)
    private List<Order> orderList;

    @OneToMany(mappedBy = "customer")
    private List<Bill> bills;



}
