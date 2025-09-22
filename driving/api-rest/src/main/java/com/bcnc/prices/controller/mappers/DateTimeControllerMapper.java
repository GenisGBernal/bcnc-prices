/*
 * Copyright (c) 2025 BCNC.
 * All rights reserved.
 */
package com.bcnc.prices.controller.mappers;

import com.bcnc.prices.controller.configs.DateTimeConfig;
import java.time.LocalDateTime;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DateTimeControllerMapper {

  default LocalDateTime toDomain(String localDateTimeStr) {
    if (localDateTimeStr == null) return null;

    return LocalDateTime.parse(localDateTimeStr, DateTimeConfig.LOCAL_DATE_TIME_FORMATTER);
  }

  default String toResponse(LocalDateTime localDateTime) {
    if (localDateTime == null) return null;

    return localDateTime.format(DateTimeConfig.LOCAL_DATE_TIME_FORMATTER);
  }
}
