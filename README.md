# Invoice PDF Generation System

This project provides an **Invoice PDF Generation** system built with **Spring Boot**, **Thymeleaf**, and **iTextRenderer**. It allows generating and downloading invoice PDFs based on the provided invoice data. The system also checks if the same invoice data has been processed before, and reuses the previously generated PDF if available, thus avoiding duplicate generation.

## Features

- Generate PDF invoices from structured data such as seller, buyer, and item details.
- Use Thymeleaf for templating and iTextRenderer for PDF generation.
- Store the generated PDFs locally with a unique identifier.
- Check for existing invoices in the database and return the file path if found.
- Save the string representation of the invoice in the database along with the file path.
- Store and reuse the PDF if the invoice data matches a previous request.

## Technologies Used

- **Java 17**
- **Spring Boot** (for REST API, service layer, and dependency management)
- **Thymeleaf** (for rendering HTML content used in PDF)
- **iTextRenderer** (for converting HTML content to PDF)
- **Lombok** (for reducing boilerplate code)
- **Spring Data JPA** (for database interaction)
- **H2/MySQL/PostgreSQL** (for persistent data storage)


## Postman Example

Below is an example of a request and response using Postman:

![Postman Example](images/postman_example.png)
