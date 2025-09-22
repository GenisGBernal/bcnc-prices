/*
 * Copyright (c) 2025 BCNC.
 * All rights reserved.
 */
package com.bcnc.prices.repository.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.bcnc.prices.controller.mappers.DateTimeControllerMapper;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DateTimeControllerMapperTest {

  public static final String LOCAL_DATE_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";

  public static final DateTimeFormatter LOCAL_DATE_TIME_FORMATTER =
      DateTimeFormatter.ofPattern(LOCAL_DATE_TIME_PATTERN);

  private DateTimeControllerMapper mapper = Mappers.getMapper(DateTimeControllerMapper.class);

  @Test
  void shouldReturnLocalDateTime_whenInputValidLocalDateTimeStr() {
    // given
    String localDateTimeStr = "2024-10-10T10:10:10";
    LocalDateTime expectedResult = LocalDateTime.parse(localDateTimeStr, LOCAL_DATE_TIME_FORMATTER);

    // when
    LocalDateTime result = mapper.toDomain(localDateTimeStr);

    // then
    assertEquals(expectedResult, result);
  }

  @Test
  void shouldReturnLocalDateTime_whenInputValidLocalDateTIme() {
    // given
    LocalDateTime localDateTime = LocalDateTime.now();
    String expectedResult = localDateTime.format(LOCAL_DATE_TIME_FORMATTER);

    // when
    String result = mapper.toResponse(localDateTime);

    // then
    assertEquals(expectedResult, result);
  }
}
