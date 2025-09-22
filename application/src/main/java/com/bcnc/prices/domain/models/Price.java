package com.bcnc.prices.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Price {

    private Long brandId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long priceListId;
    private Long productId;
    private Integer priority;
    private BigDecimal price;
    private String currency;

}

