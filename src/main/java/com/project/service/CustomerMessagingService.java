package com.project.service;

import com.project.configuration.TwilioMailConfig;
import com.project.configuration.TwilioSmsConfig;
import com.project.configuration.TwilioWhatsappConfig;
import com.project.entity.Customer;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;

@Service
public class CustomerMessagingService {

    @Autowired
    private TwilioSmsConfig configTwilio;

    @Autowired
    private TwilioMailConfig configTwilioMail;

    @Autowired
    private TwilioWhatsappConfig configTwilioWhatsapp;

    @Autowired
    private JavaMailSender javaMailSender;

    //send sms to customer passes in parameter
    public void sendSms(Customer customer){
        Message.creator(new PhoneNumber("+91"+customer.getPhoneNumber()),new PhoneNumber(configTwilio.getTrialNumber()),"Dear Customer   "+ customer.getCustomerName() +"  Your Order on the way and delivered in few of days").create();
    }

    //send email to customer passes in parameter
    public void sendMail(Customer customer){
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        System.out.println(customer.getEmail());
        simpleMailMessage.setTo(customer.getEmail());
        simpleMailMessage.setFrom(configTwilioMail.getUsername());
        simpleMailMessage.setSubject("Order Details");
        simpleMailMessage.setText("Dear Customer   "+ customer.getCustomerName() +"  Your Order on the way and delivered in few of days");
        javaMailSender.send(simpleMailMessage);
    }

    //send whatsapp message to customer passes in parameter
    public void sendWhatsappMessage(Customer customer, String imageUrl){
        Message message = Message.creator(
                new PhoneNumber("whatsapp:+91"+customer.getPhoneNumber()),
                new PhoneNumber(configTwilioWhatsapp.getTrialNumber()),
                "Dear Customer   "+ customer.getCustomerName() +"  Your Order on the way and delivered in few of days")
                .setMediaUrl(List.of(URI.create(imageUrl))).create();
    }
}
