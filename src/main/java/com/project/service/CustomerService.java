package com.project.service;

import com.project.entity.Customer;
import com.project.repository.CustomerRepo;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    public Customer addCustomer(Customer customer) {
        try {
            Customer savedCustomer = this.customerRepo.save(customer);
            if (savedCustomer != null){
                message("whatsapp:+91" + savedCustomer.getPhoneNumber());
                return savedCustomer;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void message(String phoneNumber){
        System.out.println(phoneNumber);
        Message.creator(new PhoneNumber(phoneNumber),new PhoneNumber("whatsapp:+14155238886"),"hello").create();
    }



}
