package com.project.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BillDto {

    private int billId;

    private int customerId;

    private int productId;

    private int quantity;

    private double totalAmount;
}
