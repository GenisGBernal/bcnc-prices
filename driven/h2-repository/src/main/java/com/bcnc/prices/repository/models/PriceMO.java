/*
 * Copyright (c) 2025 BCNC.
 * All rights reserved.
 */
package com.bcnc.prices.repository.models;

import com.bcnc.prices.repository.models.base.ModelEntity;
import com.bcnc.prices.repository.utils.EntityUtils;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serial;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.UuidGenerator;

@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
// @Audited
@Table(
    name = "TBL_PRICES",
    indexes = {
      @Index(
          name = "IDX_TBL_PRICES_ON_BRAND_ID_PRODUCT_ID_START_DATE_END_DATE",
          columnList = "BRAND_ID, PRODUCT_ID, START_DATE, END_DATE, PRIORITY DESC",
          unique = false)
    })
public class PriceMO implements ModelEntity<UUID> {

  @Serial private static final long serialVersionUID = -2321845881032453460L;

  @Id
  @GeneratedValue
  @UuidGenerator
  @Column(name = "ID")
  private UUID id;

  @ToString.Exclude
  @ManyToOne(
      fetch = FetchType.LAZY,
      cascade = {},
      optional = false)
  @JoinColumn(name = "BRAND_ID", referencedColumnName = "ID")
  private BrandMO brandMO;
  public static final String FIELD_BRAND = "brandMO";

  @Column(name = "START_DATE", nullable = false)
  private LocalDateTime startDate;
  public static final String FIELD_START_DATE = "startDate";

  @Column(name = "END_DATE", nullable = false)
  private LocalDateTime endDate;
  public static final String FIELD_END_DATE = "endDate";

  @ToString.Exclude
  @ManyToOne(
      fetch = FetchType.LAZY,
      cascade = {},
      optional = false)
  @JoinColumn(name = "PRICE_LIST_ID", referencedColumnName = "ID")
  private PriceListMO priceListMO;

  @ToString.Exclude
  @ManyToOne(
      fetch = FetchType.LAZY,
      cascade = {},
      optional = false)
  @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID")
  private ProductMO productMO;
  public static final String FIELD_PRODUCT = "productMO";

  @Column(name = "PRIORITY", nullable = false)
  private Integer priority;
  public static final String FIELD_PRIORITY = "priority";

  @Column(name = "PRICE", nullable = false, columnDefinition = "NUMERIC(15,2)")
  private BigDecimal price;

  @Column(name = "CURR", length = 3, nullable = false)
  private String currency;
  public static final String FIELD_CURRENCY = "currency";

  @Override
  public boolean equals(Object o) {
    return EntityUtils.equals(this, o, PriceMO::getId);
  }

  @Override
  public int hashCode() {
    return EntityUtils.hashCode(this);
  }
}
