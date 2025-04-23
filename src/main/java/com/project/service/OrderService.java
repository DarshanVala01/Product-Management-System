package com.project.service;

import com.project.dtos.BillDto;
import com.project.dtos.OrderResponseDto;
import com.project.entity.Bill;
import com.project.entity.Customer;
import com.project.entity.Order;
import com.project.entity.Product;
import com.project.repository.CustomerRepo;
import com.project.repository.OrderRepo;
import com.project.repository.ProductRepo;
import org.springframework.beans.BeanInfoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private BillService billService;


    public OrderResponseDto addOrder(Order order) {
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        try{
            Customer customer = this.customerRepo.findById(order.getCustomer().getCustomerId()).get();
            Product product = this.productRepo.findById(order.getProduct().getProductId()).get();

            // check product quantity of product is available or not
            if (order.getQuantity() > product.getInventoryCount()){
                orderResponseDto.setBill(null);
                orderResponseDto.setMessage("Order not placed due insufficient stock");
                orderResponseDto.setHttpStatus(HttpStatus.BAD_REQUEST);
                return orderResponseDto;
            }

            Bill bill = this.billService.createBill(order);

            if (bill != null){

                // set properties into OrderResponseDto object
                orderResponseDto = new OrderResponseDto();
                orderResponseDto.setBill(new BillDto(bill.getBillId(),bill.getCustomer().getCustomerId(),
                        bill.getProduct().getProductId(),bill.getQuantity(),bill.getTotalAmountPayAble()));
                orderResponseDto.setMessage("Order added");
                orderResponseDto.setHttpStatus(HttpStatus.OK);

            }else {
                //order is not placed whether payment is not done
                orderResponseDto=new OrderResponseDto();
                orderResponseDto.setBill(null);
                orderResponseDto.setMessage("Payment failed");
                orderResponseDto.setHttpStatus(HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return orderResponseDto;
    }

}
