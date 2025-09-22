package com.bcnc.prices.controller.adapters;

import com.bcnc.prices.api.rest.PricesApi;
import com.bcnc.prices.api.rest.dto.ActivePriceDTO;
import com.bcnc.prices.application.exceptions.BadRequestException;
import com.bcnc.prices.application.ports.driving.PriceUseCasePort;
import com.bcnc.prices.controller.mappers.DateTimeControllerMapper;
import com.bcnc.prices.controller.mappers.PriceControllerMapper;
import com.bcnc.prices.domain.models.values.ActivePrice;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class PriceControllerAdapter implements PricesApi {

    private final PriceUseCasePort useCasePort;
    private final PriceControllerMapper mapper;
    private final DateTimeControllerMapper dateTimeControllerMapper;

    @Override
    public ResponseEntity<ActivePriceDTO> getActivePrice(String date, Long productId, Long brandId) {
        LocalDateTime dateReq;
        try {
            dateReq = dateTimeControllerMapper.toDomain(date);
        } catch (Exception e) {
            throw new BadRequestException();
        }

        ActivePrice activePrice = useCasePort.getActivePrice(dateReq, productId, brandId);
        ActivePriceDTO activePriceDTO = mapper.toResponse(activePrice);
        return ResponseEntity.ok(activePriceDTO);
    }

}
