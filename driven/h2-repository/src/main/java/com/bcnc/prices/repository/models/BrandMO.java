/*
 * Copyright (c) 2025 BCNC.
 * All rights reserved.
 */
package com.bcnc.prices.repository.models;

import com.bcnc.prices.repository.utils.EntityUtils;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name = "TBL_BRANDS")
public class BrandMO implements ModelEntity<Long> {

  @Serial private static final long serialVersionUID = -8321869081032453460L;

  @Id
  @Column(name = "ID", nullable = false)
  private Long id;

  @Column(name = "NAME", nullable = false)
  private String name;

  @ToString.Exclude
  @Builder.Default
  @OneToMany(
      fetch = FetchType.LAZY,
      orphanRemoval = false,
      cascade = {},
      mappedBy = "brandMO")
  private List<PriceMO> pricesMO = new ArrayList<>();

  @Override
  public boolean equals(Object o) {
    return EntityUtils.equals(this, o, BrandMO::getId);
  }

  @Override
  public int hashCode() {
    return EntityUtils.hashCode(this);
  }
}
