package com.example.PDFGenerator.transformer;

import com.example.PDFGenerator.dto.InvoiceRequest;
import com.example.PDFGenerator.model.Invoice;

import java.io.File;

public class InvoiceTransformer {

    public static Invoice invoiceRequestToInvoice(InvoiceRequest invoiceRequest, File pdf) {
        return Invoice.builder()
                .jsonString(invoiceRequest.toString())
                .filePath(pdf.getPath())
                .build();
    }
}
