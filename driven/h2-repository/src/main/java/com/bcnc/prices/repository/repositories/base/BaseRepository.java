/*
 * Copyright (c) 2025 BCNC.
 * All rights reserved.
 */
package com.bcnc.prices.repository.repositories.base;

import com.bcnc.prices.repository.models.ModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @param <E> the type of the entity
 * @param <ID> the type of the identifier of the entity
 */
@NoRepositoryBean
public interface BaseRepository<E extends ModelEntity<ID>, ID>
    extends JpaRepository<E, ID>, JpaSpecificationExecutor<E> {}
