package com.project.service;

import com.project.configuration.AdminConfig;
import com.project.configuration.TwilioMailConfig;
import com.project.configuration.TwilioSmsConfig;
import com.project.configuration.TwilioWhatsappConfig;
import com.project.entity.Product;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class AdminMessagingService {

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private TwilioSmsConfig twilioSmsConfig;

    @Autowired
    private TwilioMailConfig twilioMailConfig;

    @Autowired
    private TwilioWhatsappConfig twilioWhatsappConfig;

    @Autowired
    private JavaMailSender javaMailSender;


    public void sendSms(Product product) {
        Message.creator(new PhoneNumber(adminConfig.getPhoneNumber()),
                new PhoneNumber(twilioSmsConfig.getTrialNumber()),
                "⚠Alert "+product.getTitle()+" remaining only "+product.getInventoryCount()).create();
    }

    public void sendEmail(Product product) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        // set properties of mail
        simpleMailMessage.setTo(adminConfig.getEmail());
        simpleMailMessage.setFrom(twilioMailConfig.getUsername());
        simpleMailMessage.setSubject("Reminder");
        simpleMailMessage.setText("⚠Alert "+product.getTitle()+" remaining only "+product.getInventoryCount());

        javaMailSender.send(simpleMailMessage);
    }

    public void sendWhatsapp(Product product) {
        Message.creator(new PhoneNumber(adminConfig.getWhatsappNumber()),
                new PhoneNumber(twilioWhatsappConfig.getTrialNumber()),
                "⚠Alert "+product.getTitle()+" remaining only "+product.getInventoryCount()).create();
    }

    public void sendReportOfStock(){

    }
}
