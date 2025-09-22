package com.zara.prices.repository.mappers;

import com.zara.prices.api.rest.dto.ActivePriceDTO;
import com.zara.prices.controller.mappers.DateTimeControllerMapper;
import com.zara.prices.controller.mappers.PriceControllerMapper;
import com.zara.prices.domain.models.values.ActivePrice;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

public class PriceControllerMapperTest {

    @Spy
    @InjectMocks
    private PriceControllerMapper mapper = Mappers.getMapper(PriceControllerMapper.class);
    @Mock
    private DateTimeControllerMapper stringControllerMapper;

    @Test
    void toResponse_shouldReturnActivePriceDTO_whenInputValidActivePrice() {
        ActivePrice base = Instancio.create(ActivePrice.class);

        String expectedStartDate = "2025-10-10T10:10:10";
        when(stringControllerMapper.toResponse(base.startDate()))
            .thenReturn(expectedStartDate);

        String expectedEndDate = "2025-13-13T13:13:14";
        when(stringControllerMapper.toResponse(base.endDate()))
            .thenReturn(expectedEndDate);

        ActivePriceDTO result = mapper.toResponse(base);

        assertEquals(base.brandId(), result.getBrandId());
        assertEquals(expectedStartDate, result.getStartDate());
        assertEquals(expectedEndDate, result.getEndDate());
        assertEquals(base.priceListId(), result.getPriceListId());
        assertEquals(base.productId(), result.getProductId());
        assertEquals(base.currency(), result.getCurrency());
    }
}
