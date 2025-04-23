package com.project.controller;

import com.project.dtos.OrderResponseDto;
import com.project.entity.Order;
import com.project.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/add-order")
    public OrderResponseDto addOrder(@RequestBody @Valid Order order){
        OrderResponseDto orderResponseDto=orderService.addOrder(order);
        if(orderResponseDto!=null){
            return orderResponseDto;
        }
        return orderResponseDto;
    }


}
