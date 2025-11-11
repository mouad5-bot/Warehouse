package com.progresssoft.warehouse.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FXDealDtoRequest {

    @NotBlank(message = "Ordering currency ISO code cannot be blank")
    @Size(min = 3, max = 255, message = "Ordering currency ISO code must be between 3 and 255 characters")
    private String orderingCurrencyIsoCode;

    @NotBlank(message = "Target currency ISO code cannot be blank")
    @Size(min = 3, max = 255, message = "Target currency ISO code must be between 3 and 255 characters")
    private String toCurrencyIsoCode;

    @NotNull(message = "Deal amount is required")
    @Positive(message = "Deal amount must be a positive value")
    private Double dealAmount;
}