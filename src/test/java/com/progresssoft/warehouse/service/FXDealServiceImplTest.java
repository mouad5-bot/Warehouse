package com.progresssoft.warehouse.service;

import com.progresssoft.warehouse.aop.exception.AlreadyExistException;
import com.progresssoft.warehouse.dto.request.FXDealDtoRequest;
import com.progresssoft.warehouse.dto.response.FXDealDtoResponse;
import com.progresssoft.warehouse.model.FXDeal;
import com.progresssoft.warehouse.repository.FXDealRepository;
import com.progresssoft.warehouse.service.impl.FXDealServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FXDealServiceImplTest {

    @Mock
    private FXDealRepository fxDealRepository;

    @Mock
    private IMapper mapper;

    @InjectMocks
    private FXDealServiceImpl fxDealService;

    @Test
    void save_ShouldSaveNewDeal_WhenNotExists() {
        FXDealDtoRequest request = new FXDealDtoRequest();
        request.setOrderingCurrencyIsoCode("USD");
        request.setToCurrencyIsoCode("EUR");
        request.setDealAmount(1000.0);

        FXDeal entity = new FXDeal();
        FXDeal savedEntity = new FXDeal();
        FXDealDtoRequest mappedBack = new FXDealDtoRequest();

        when(fxDealRepository.existsByOrderingCurrencyIsoCodeAndToCurrencyIsoCodeAndDealAmount(
                "USD", "EUR", 1000.0))
                .thenReturn(false);
        when(mapper.map(request, FXDeal.class)).thenReturn(entity);
        when(fxDealRepository.save(entity)).thenReturn(savedEntity);
        when(mapper.map(savedEntity, FXDealDtoRequest.class)).thenReturn(mappedBack);

        FXDealDtoRequest result = fxDealService.save(request);

        assertEquals(mappedBack, result);
        verify(fxDealRepository).save(entity);
    }

    @Test
    void save_ShouldThrowException_WhenDealAlreadyExists() {
        FXDealDtoRequest request = new FXDealDtoRequest();
        request.setOrderingCurrencyIsoCode("USD");
        request.setToCurrencyIsoCode("EUR");
        request.setDealAmount(500.0);

        when(fxDealRepository.existsByOrderingCurrencyIsoCodeAndToCurrencyIsoCodeAndDealAmount(
                "USD", "EUR", 500.0))
                .thenReturn(true);

        assertThrows(AlreadyExistException.class, () -> fxDealService.save(request));
        verify(fxDealRepository, never()).save(any());
    }

    @Test
    void getAll_ShouldReturnMappedDeals() {
        FXDeal deal1 = new FXDeal();
        FXDeal deal2 = new FXDeal();
        FXDealDtoResponse dto1 = new FXDealDtoResponse();
        FXDealDtoResponse dto2 = new FXDealDtoResponse();

        when(fxDealRepository.findAll()).thenReturn(List.of(deal1, deal2));
        when(mapper.mapList(List.of(deal1, deal2), FXDealDtoResponse.class))
                .thenReturn(List.of(dto1, dto2));

        List<FXDealDtoResponse> result = fxDealService.getAll();

        assertEquals(2, result.size());
        assertSame(dto1, result.get(0));
        assertSame(dto2, result.get(1));
        verify(fxDealRepository).findAll();
    }
}
