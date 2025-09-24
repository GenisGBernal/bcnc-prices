/*
 * Copyright (c) 2025 BCNC.
 * All rights reserved.
 */
package com.bcnc.prices.repository.models;

import java.io.Serializable;

public interface ModelEntity<ID> extends Serializable {

  ID getId();

  boolean equals(Object o);

  int hashCode();
}
