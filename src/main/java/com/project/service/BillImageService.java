package com.project.service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.nio.file.Files;
import java.time.LocalDate;

@Service
public class BillImageService {

    @Autowired
    private TemplateEngine templateEngine;

    public File generateBillImage(int billNo, String customerName, String productName,int quantity,double total) {
        Context context = new Context();
        context.setVariable("billNo", billNo);
        context.setVariable("name", customerName);
        context.setVariable("product", productName);
        context.setVariable("quantity", quantity);
        context.setVariable("total", total);

        String html = templateEngine.process("bill", context);

        try{
            // Write to temp HTML file
            File htmlFile = File.createTempFile("bill-", ".html");
            Files.write(htmlFile.toPath(), html.getBytes());

            // Generate image using wkhtmltoimage
            File imageFile = File.createTempFile("bill-", ".jpg");
            ProcessBuilder pb = new ProcessBuilder(
                    "C:\\Program Files\\wkhtmltopdf\\bin\\wkhtmltoimage.exe",
                    htmlFile.getAbsolutePath(),
                    imageFile.getAbsolutePath()
            );
            Process process = pb.start();
            process.waitFor();

            return imageFile;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
