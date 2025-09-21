package com.zara.prices.repository.models.base;

import java.io.Serializable;

public interface ModelEntity<ID> extends Serializable {

    ID getId();

    boolean equals(Object o);

    int hashCode();

}
