package com.zara.prices.controller.mappers;

import com.zara.prices.controller.configs.DateTimeConfig;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;

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
