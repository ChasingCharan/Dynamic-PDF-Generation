package com.example.PDFGenerator.controller;

import com.example.PDFGenerator.dto.InvoiceRequest;
import com.example.PDFGenerator.service.InvoiceService;
import com.itextpdf.text.DocumentException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileNotFoundException;

@RestController
@RequestMapping("/api/v1/pdf")
@RequiredArgsConstructor
public class InvoiceCollector {

    private final InvoiceService invoiceService;

    @PostMapping
    public ResponseEntity<FileSystemResource> generatePdf(@RequestBody InvoiceRequest invoiceRequest) throws DocumentException, FileNotFoundException {
        File pdfFile = invoiceService.generatePdf(invoiceRequest);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + pdfFile.getName() + "\"");
        headers.add(HttpHeaders.CONTENT_TYPE, "application/pdf");

        FileSystemResource resource = new FileSystemResource(pdfFile);
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }
}
