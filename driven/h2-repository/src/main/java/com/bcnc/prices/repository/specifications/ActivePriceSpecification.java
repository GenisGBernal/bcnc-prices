/*
 * Copyright (c) 2025 BCNC.
 * All rights reserved.
 */
package com.bcnc.prices.repository.specifications;

import com.bcnc.prices.domain.filters.active_price.ActivePriceFilter;
import com.bcnc.prices.repository.models.PriceMO;
import com.bcnc.prices.repository.models.fields.BrandFields;
import com.bcnc.prices.repository.models.fields.PriceFields;
import com.bcnc.prices.repository.models.fields.ProductFields;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@RequiredArgsConstructor(staticName = "of")
public class ActivePriceSpecification implements Specification<PriceMO> {

  private final ActivePriceFilter filter;

  @Override
  public Predicate toPredicate(Root<PriceMO> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
    // Create correlated subquery
    Subquery<Integer> prioritySubquery = query.subquery(Integer.class);
    Root<PriceMO> subRoot = prioritySubquery.from(PriceMO.class);

    prioritySubquery.select(cb.max(subRoot.get(PriceFields.PRIORITY)));
    prioritySubquery.where(
        cb.equal(
            subRoot.get(PriceFields.PRODUCT).get(ProductFields.ID),
            root.get(PriceFields.PRODUCT).get(ProductFields.ID)),
        cb.equal(
            subRoot.get(PriceFields.BRAND).get(BrandFields.ID),
            root.get(PriceFields.BRAND).get(BrandFields.ID)),
        cb.equal(subRoot.get(PriceFields.CURRENCY), root.get(PriceFields.CURRENCY)),
        cb.between(
            cb.literal(filter.date()),
            subRoot.get(PriceFields.START_DATE),
            subRoot.get(PriceFields.END_DATE)));

    // Main predicates
    Predicate productPredicate =
        cb.equal(root.get(PriceFields.PRODUCT).get(ProductFields.ID), filter.productId());
    Predicate brandPredicate =
        cb.equal(root.get(PriceFields.BRAND).get(BrandFields.ID), filter.brandId());
    Predicate datePredicate =
        cb.between(
            cb.literal(filter.date()),
            root.get(PriceFields.START_DATE),
            root.get(PriceFields.END_DATE));
    Predicate maxPriorityPredicate = cb.equal(root.get(PriceFields.PRIORITY), prioritySubquery);

    return cb.and(productPredicate, brandPredicate, datePredicate, maxPriorityPredicate);
  }
}
