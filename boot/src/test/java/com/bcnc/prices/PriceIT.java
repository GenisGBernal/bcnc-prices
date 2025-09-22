/*
 * Copyright (c) 2025 BCNC.
 * All rights reserved.
 */
package com.bcnc.prices;

import com.bcnc.prices.utils.TestUtils;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ActiveProfiles("it")
@SpringBootTest(
    classes = Application.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(
    scripts = "/data/getActivePriceTestData.sql",
    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@RequiredArgsConstructor
public class PriceIT {

  private final WebApplicationContext webApplicationContext;

  private MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }

  private static Stream<Arguments> testSourceGetActivePrice() {
    return Stream.of(
        Arguments.of(
            "Test 1: petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)",
            "2020-06-14T10:00:00",
            "35455",
            "1",
            TestUtils.readJson("/expected_responses/getActivePriceTest1.json")),
        Arguments.of(
            "Test 2: petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA)",
            "2020-06-14T16:00:00",
            "35455",
            "1",
            TestUtils.readJson("/expected_responses/getActivePriceTest2.json")),
        Arguments.of(
            "Test 3: petición a las 21:00 del día 14 del producto 35455 para la brand 1 (ZARA)",
            "2020-06-14T21:00:00",
            "35455",
            "1",
            TestUtils.readJson("/expected_responses/getActivePriceTest3.json")),
        Arguments.of(
            "Test 4: petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA)",
            "2020-06-15T10:00:00",
            "35455",
            "1",
            TestUtils.readJson("/expected_responses/getActivePriceTest4.json")),
        Arguments.of(
            "Test 5: petición a las 21:00 del día 16 del producto 35455 para la brand 1 (ZARA)",
            "2020-06-16T21:00:00",
            "35455",
            "1",
            TestUtils.readJson("/expected_responses/getActivePriceTest5.json")));
  }

  @SneakyThrows
  @ParameterizedTest(name = "{0}")
  @MethodSource("testSourceGetActivePrice")
  void testGetActivePrice(
      String displayName, String dateTime, String productId, String brandId, String expectedJson) {
    mockMvc
        .perform(
            MockMvcRequestBuilders.get("/prices/active")
                .param("date", dateTime)
                .param("productId", productId)
                .param("brandId", brandId))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
        .andDo(
            result ->
                JSONAssert.assertEquals(
                    expectedJson, result.getResponse().getContentAsString(), false));
  }
}
