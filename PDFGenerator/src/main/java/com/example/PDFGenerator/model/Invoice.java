package com.example.PDFGenerator.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    @Lob
    @Column(nullable = false)
    String jsonString;
    @Column(nullable = false)
    String filePath;

}
