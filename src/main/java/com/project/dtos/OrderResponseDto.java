package com.project.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class OrderResponseDto {

    private BillDto bill;

    private String message;

    private HttpStatus httpStatus;

    public OrderResponseDto() {
        this.message="order not placed";
        this.httpStatus=HttpStatus.BAD_REQUEST;
    }

    public OrderResponseDto(HttpStatus httpStatus, String message, BillDto bill) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.bill = bill;
    }
}
