package com.bcnc.prices.use_cases;

import com.bcnc.prices.application.exceptions.NotFoundException;
import com.bcnc.prices.application.services.PriceService;
import com.bcnc.prices.application.use_cases.PriceUseCase;
import com.bcnc.prices.domain.models.values.ActivePrice;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
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
public class PriceUseCaseTest {

    @InjectMocks
    private PriceUseCase useCase;
    @Mock
    private PriceService priceService;

    @Nested
    class GetActivePrice {

        @Test
        void shouldReturnActivePrice_whenServiceReturnsValue() {
            // given
            LocalDateTime date = LocalDateTime.now();
            Long productId = 3424L;
            Long brandId = 3L;

            ActivePrice expected = mock(ActivePrice.class);
            when(priceService.findActivePrice(date, productId, brandId))
                .thenReturn(Optional.of(expected));

            // when
            ActivePrice result = useCase.getActivePrice(date, productId, brandId);

            // then
            assertEquals(expected, result);
        }

        @Test
        void shouldThrowNotFoundException_whenServiceReturnsEmpty() {
            // given
            LocalDateTime date = LocalDateTime.now();
            Long productId = 3424L;
            Long brandId = 3L;

            when(priceService.findActivePrice(date, productId, brandId))
                .thenReturn(Optional.empty());

            // when
            Executable executable = () -> useCase.getActivePrice(date, productId, brandId);

            // then
            assertThrows(NotFoundException.class, executable);
        }
    }
}
