package com.progresssoft.warehouse.web.rest;

import com.progresssoft.warehouse.dto.response.FXDealDtoResponse;
import com.progresssoft.warehouse.service.impl.FXDealServiceImpl;
import com.progresssoft.warehouse.dto.request.FXDealDtoRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/FXDeals")
public class FXDealsController {
    private final FXDealServiceImpl fxDealsService;

    @PostMapping
    public ResponseEntity<FXDealDtoRequest> save(@Valid @RequestBody FXDealDtoRequest dealsDto) {
        return new ResponseEntity<>(fxDealsService.save(dealsDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<FXDealDtoResponse>> getAll() {
        return ResponseEntity.ok(fxDealsService.getAll());
    }
}