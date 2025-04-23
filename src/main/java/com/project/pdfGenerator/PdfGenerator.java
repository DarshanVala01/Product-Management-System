package com.project.pdfGenerator;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import java.io.File;
import java.io.FileNotFoundException;

public class PdfGenerator{

    public static File generateBill(String customerName, String itemName, int quantity, int price,double gst,String filePath) throws FileNotFoundException {
        // create object of file
        File pdfFile = new File(filePath);

        PdfWriter writer = new PdfWriter(pdfFile);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        document.add(new Paragraph("Bill Receipt"));
        document.add(new Paragraph("Customer Name: " + customerName));
        document.add(new Paragraph("Item: " + itemName));
        document.add(new Paragraph("Quantity: " + quantity));
        document.add(new Paragraph("Price per unit: $" + price));
        document.add(new Paragraph("Total GST : $" + gst));
        double totalPrice = (quantity * price) + gst;
        document.add(new Paragraph("Total: $" + totalPrice));
        document.close();

        return pdfFile;
    }
}
