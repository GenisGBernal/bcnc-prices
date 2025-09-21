package com.zara.prices.repository.repositories;

import com.zara.prices.repository.models.PriceMO;
import com.zara.prices.repository.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PriceRepository extends BaseRepository<PriceMO, UUID> {
}
