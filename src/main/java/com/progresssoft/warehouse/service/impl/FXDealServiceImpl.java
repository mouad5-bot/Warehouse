package com.progresssoft.warehouse.service.impl;

import com.progresssoft.warehouse.aop.exception.AlreadyExistException;
import com.progresssoft.warehouse.model.FXDeal;
import com.progresssoft.warehouse.repository.FXDealRepository;
import com.progresssoft.warehouse.service.FXDealService;
import com.progresssoft.warehouse.dto.request.FXDealDtoRequest;
import com.progresssoft.warehouse.dto.response.FXDealDtoResponse;
import com.progresssoft.warehouse.service.IMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FXDealServiceImpl implements FXDealService {
    private final FXDealRepository fxDealsRepository;
    private final IMapper mapper;

    @Override
    public FXDealDtoRequest save(FXDealDtoRequest newFXDealsDto) {

        boolean exists = fxDealsRepository.existsByOrderingCurrencyIsoCodeAndToCurrencyIsoCodeAndDealAmount(
                newFXDealsDto.getOrderingCurrencyIsoCode(),
                newFXDealsDto.getToCurrencyIsoCode(),
                newFXDealsDto.getDealAmount()
        );

        if (exists) {
            throw new AlreadyExistException("Deal already exists");
        }

        return mapper.map(
                fxDealsRepository.save(
                        mapper.map(newFXDealsDto, FXDeal.class)
                ),
                FXDealDtoRequest.class
        );
    }

    @Override
    public List<FXDealDtoResponse> getAll() {
        return mapper.mapList(fxDealsRepository.findAll(), FXDealDtoResponse.class);
    }

}
