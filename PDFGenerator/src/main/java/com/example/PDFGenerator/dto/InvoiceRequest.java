package com.example.PDFGenerator.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class InvoiceRequest {
    String seller;
    String sellerGstin;
    String sellerAddress;
    String buyer;
    String buyerGstin;
    String buyerAddress;
    List<Item> items;

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Seller: ").append(seller).append(", ");
        sb.append("Seller GSTIN: ").append(sellerGstin).append(", ");
        sb.append("Seller Address: ").append(sellerAddress).append(", ");
        sb.append("Buyer: ").append(buyer).append(", ");
        sb.append("Buyer GSTIN: ").append(buyerGstin).append(", ");
        sb.append("Buyer Address: ").append(buyerAddress).append(", ");

        sb.append("Items: [");
        for (Item item : items) {
            sb.append(item.toString()).append(", ");
        }

        if (!items.isEmpty()) {;
            sb.setLength(sb.length() - 2);
        }
        sb.append("]");

        return sb.toString();

    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Setter
    @Getter
    @Builder
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Item{
        String name;
        String quantity;
        double rate;
        double amount;

        public String toString() {
            return String.format("Name: %s, Quantity: %s, Rate: %.2f, Amount: %.2f",
                    name, quantity, rate, amount);
        }
    }

}
