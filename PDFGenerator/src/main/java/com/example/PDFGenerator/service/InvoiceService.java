package com.example.PDFGenerator.service;

import com.example.PDFGenerator.dto.InvoiceRequest;
import com.example.PDFGenerator.model.Invoice;
import com.example.PDFGenerator.repository.InvoiceRepository;
import com.example.PDFGenerator.transformer.InvoiceTransformer;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class InvoiceService {
    public final InvoiceRepository invoiceRepository;
    public final SpringTemplateEngine templateEngine;

    public File generatePdf(InvoiceRequest invoiceRequest) throws FileNotFoundException {

        String dataString = invoiceRequest.toString();

        Optional<Invoice> existingInvoice = invoiceRepository.findByJsonString(dataString);
        if (existingInvoice.isPresent()) {
            return new File(existingInvoice.get().getFilePath());
        }


        String htmlContent = generateHtmlContent(invoiceRequest);

        File pdf = createPdfFromHtml(htmlContent);

        Invoice invoice = InvoiceTransformer.invoiceRequestToInvoice(invoiceRequest,pdf);

        Invoice saveInvoice = invoiceRepository.save(invoice);

        return pdf;



    }

    private File createPdfFromHtml(String htmlContent) throws FileNotFoundException {

        String invoiceDirPath = System.getProperty("user.home") + File.separator + "invoices";
        File invoiceDir = new File(invoiceDirPath);

        if (!invoiceDir.exists()) {
            invoiceDir.mkdirs();
        }

        String uniqueId = UUID.randomUUID().toString();
        String outputFolder = invoiceDirPath + File.separator + uniqueId + "_invoice.pdf";

        File pdfFile = new File(outputFolder);
        OutputStream outputStream = new FileOutputStream(pdfFile);
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(htmlContent);
        renderer.layout();
        renderer.createPDF(outputStream);

        return pdfFile;
    }

    private String generateHtmlContent(InvoiceRequest invoiceRequest) {
        Context context = new Context();
        context.setVariable("seller", invoiceRequest.getSeller());
        context.setVariable("sellerGstin", invoiceRequest.getSellerGstin());
        context.setVariable("sellerAddress", invoiceRequest.getSellerAddress());
        context.setVariable("buyer", invoiceRequest.getBuyer());
        context.setVariable("buyerGstin", invoiceRequest.getBuyerGstin());
        context.setVariable("buyerAddress", invoiceRequest.getBuyerAddress());
        List<InvoiceRequest.Item> items = invoiceRequest.getItems();
        context.setVariable("items", items);

        return templateEngine.process("invoice", context);
    }

}
