package com.progresssoft.warehouse.service;

import com.progresssoft.warehouse.dto.request.FXDealDtoRequest;
import com.progresssoft.warehouse.dto.response.FXDealDtoResponse;

import java.util.List;

public interface FXDealService {
    FXDealDtoRequest save(final FXDealDtoRequest newFXDealsDto);
    List<FXDealDtoResponse> getAll();
}
