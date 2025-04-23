package com.project.service;

import com.project.entity.Bill;
import com.project.entity.Customer;
import com.project.entity.Order;
import com.project.entity.Product;
import com.project.repository.BillRepo;
import com.project.repository.CustomerRepo;
import com.project.repository.OrderRepo;
import com.project.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Random;

@Service
public class BillService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ProductService productService;

    @Autowired
    private AdminMessagingService adminMessagingService;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private BillImageService billImageService;

    @Autowired
    private CustomerMessagingService customerMessagingService;

    @Autowired
    private BillRepo billRepo;

    public Bill createBill(Order order){
        Bill bill = null;
        try {
            bill = new Bill();
            Product product = this.productRepo.findById(order.getProduct().getProductId()).get();
            Customer customer = this.customerRepo.findById(order.getCustomer().getCustomerId()).get();

            // set address in bill object
            bill.setAddress(order.getAddress());

            // add quantity in bill
            bill.setQuantity(order.getQuantity());

            // calculate total amount with gst included
            double payAbleAmount = product.getCurrentPrice()+((product.getCurrentPrice()*product.getGst()) / 100);

            // add total amount into bill
            bill.setTotalAmountPayAble(payAbleAmount * order.getQuantity());

            //by direction relationship between bill and order
            bill.setOrder(order);
            order.setBill(bill);

            //by direction relationship between bill and customer
            bill.setCustomer(customer);
            customer.getBills().add(bill);

            //by direction relationship between bill and product
            bill.setProduct(product);
            product.getBills().add(bill);

            //remove ordered quantity from inventory count
            product.setInventoryCount(product.getInventoryCount()-order.getQuantity());

            if(!payment()){
                return null;
            }

            orderRepo.save(order);
            billRepo.save(bill);
            customerRepo.save(customer);
            productRepo.save(product);


            //check if product stock is less then threshold value
            if(productService.isLessThenThresHold(product.getInventoryCount())){
                adminMessagingService.sendSms(product);
                adminMessagingService.sendEmail(product);
                adminMessagingService.sendWhatsapp(product);
            }

            // create pdf and store it on cloud
            File file = this.billImageService.generateBillImage(bill.getBillId(), bill.getCustomer().getCustomerName(), bill.getProduct().getTitle(),bill.getQuantity(),bill.getTotalAmountPayAble());
            String imageUrl = this.cloudinaryService.uploadFile(file);

            System.out.println("PDF uploaded at: " + imageUrl);

            //at last we will send sms ,email,whatsapp message to customer
            customerMessagingService.sendSms(customer);
            customerMessagingService.sendMail(customer);
            customerMessagingService.sendWhatsappMessage(customer,imageUrl);

        }catch (Exception e){
            e.printStackTrace();
        }
        return bill;
    }

    // payment method is under process
    public boolean payment(){
        return new Random().nextBoolean();
    }
}
