package com.surya.lowesLiveCoding.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer transId;

    @NotNull(message="Should not be null")
    private String stockTicker;
    private String stockName;

    private Integer quantityPurchased;

    private Double amountSpent;
    private String country;
    private LocalDateTime createTs;

    @PrePersist
    public void setCreateTs() {
        this.createTs = LocalDateTime.now();
    }

    public Stock(String stockTicker, String stockName, Integer quantityPurchased, Double amountSpent, String country) {
        this.stockTicker = stockTicker;
        this.stockName = stockName;
        this.quantityPurchased = quantityPurchased;
        this.amountSpent = amountSpent;
        this.country = country;
    }
}
