package com.project.service;

import com.project.configuration.AdminConfig;
import com.project.configuration.TwilioMailConfig;
import com.project.entity.Product;
import com.project.repository.ProductRepo;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class StockReportService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private AdminMessagingService adminMessagingService;

    // make stock report
    public void sendStockReport() {
        Date date=new Date(System.currentTimeMillis());
        SimpleDateFormat sm=new SimpleDateFormat("dd-MM-yyyy");
        String format=sm.format(date);

        String filePath = "C:\\Users\\HELLO\\Desktop\\Bill Generation System\\src\\main\\resources\\invoices\\stock-report("+format+").csv";

        try(Writer writer=new FileWriter(filePath);
            ICsvBeanWriter iCsvBeanWriter = new CsvBeanWriter(writer, CsvPreference.STANDARD_PREFERENCE)){

            String[] headers={"productId","title","inventoryCount"};
            iCsvBeanWriter.writeHeader(headers);

            List<Product> products = productRepo.findAll();

            for (Product product : products){
                iCsvBeanWriter.write(product,headers);
            }

            iCsvBeanWriter.close();

            // this method send report on admin email
            sendCsvReportToAdmin(filePath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendCsvReportToAdmin(String filePath) {
        try{
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(adminConfig.getEmail()); // Admin email
            helper.setSubject("Daily Stock Report");
            helper.setText("Hello Admin,\n\nPlease find attached the daily stock report CSV.\n\nRegards,\nTeam");

            FileSystemResource file = new FileSystemResource(new File(filePath));
            helper.addAttachment("stock-report.csv", file);

            javaMailSender.send(message);
            System.out.println("Mail sent successfully with attachment.");
        }catch (Exception e){

        }
    }
}
