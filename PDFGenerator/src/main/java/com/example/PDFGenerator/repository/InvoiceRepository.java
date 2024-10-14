package com.example.PDFGenerator.repository;

import com.example.PDFGenerator.model.Invoice;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {


    Optional<Invoice> findByJsonString(String dataString);
}
