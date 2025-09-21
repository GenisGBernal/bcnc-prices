package com.zara.prices.repository.models;

import com.zara.prices.repository.models.base.ModelEntity;
import com.zara.prices.repository.utils.EntityUtils;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name = "TBL_PRICE_LIST")
public class PriceListMO implements ModelEntity<Long> {

    @Serial
    private static final long serialVersionUID = 5321869081032465360L;

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
        mappedBy = "priceListMO")
    private List<PriceMO> pricesMO = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        return EntityUtils.equals(this, o, PriceListMO::getId);
    }

    @Override
    public int hashCode() {
        return EntityUtils.hashCode(this);
    }
}
