package com.bcnc.prices.services;

import com.bcnc.prices.application.ports.driven.PriceRepositoryPort;
import com.bcnc.prices.application.services.PriceService;
import com.bcnc.prices.domain.models.values.ActivePrice;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PriceServiceTest {

    @InjectMocks
    private PriceService service;
    @Mock
    private PriceRepositoryPort priceRepositoryPort;

    @Nested
    class FindActivePrice {

        @Test
        void shouldForwardOptional_whenRepositoryPortReturnsValue() {
            // given
            LocalDateTime date = LocalDateTime.now();
            Long productId = 3424L;
            Long brandId = 3L;

            Optional<ActivePrice> expected = mock(Optional.class);
            when(priceRepositoryPort.findActivePrice(date, productId, brandId))
                .thenReturn(expected);

            // when
            Optional<ActivePrice> result = service.findActivePrice(date, productId, brandId);

            // then
            assertEquals(expected, result);
        }

    }
}
