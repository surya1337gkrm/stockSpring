package com.surya.lowesLiveCoding.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockDTO {
    @NotNull(message="Not null")
    private String stockTicker;
    private String stockName;
    private Integer quantityPurchased;

    private Double amountSpent;
    private String country;
}

