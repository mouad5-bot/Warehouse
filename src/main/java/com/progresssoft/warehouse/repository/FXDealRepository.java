package com.progresssoft.warehouse.repository;

import com.progresssoft.warehouse.model.FXDeal;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FXDealRepository extends JpaRepository<FXDeal, String> {
    boolean existsByOrderingCurrencyIsoCodeAndToCurrencyIsoCodeAndDealAmount(@NotBlank(message = "Ordering currency ISO code cannot be blank") @Size(min = 3, max = 255, message = "Ordering currency ISO code must be between 3 and 255 characters") String orderingCurrencyIsoCode, @NotBlank(message = "Target currency ISO code cannot be blank") @Size(min = 3, max = 255, message = "Target currency ISO code must be between 3 and 255 characters") String toCurrencyIsoCode, @NotNull(message = "Deal amount is required") @Positive(message = "Deal amount must be a positive value") Double dealAmount);
}
