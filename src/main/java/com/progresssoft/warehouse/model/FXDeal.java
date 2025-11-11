package com.progresssoft.warehouse.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "deals")
public class FXDeal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Ordering currency ISO code is required.")
    @NotEmpty(message = "Ordering currency ISO code cannot be empty.")
    @Size(min = 3, max = 255, message = "Ordering currency ISO code must be between 3 and 255 characters.")
    @Column(nullable = false)
    private String orderingCurrencyIsoCode;

    @NotNull(message = "Target currency ISO code is required.")
    @NotEmpty(message = "Target currency ISO code cannot be empty.")
    @Size(min = 3, max = 255, message = "Target currency ISO code must be between 3 and 255 characters.")
    @Column(nullable = false)
    private String toCurrencyIsoCode;

    @CreationTimestamp
    private LocalDateTime dealTimestamp;

    @NotNull(message = "Deal amount is required.")
    @Max(value = (long) Double.MAX_VALUE, message = "Deal amount is too large.")
    @Min(value = 0, message = "Deal amount must be a positive value.")
    @Column(nullable = false)
    private Double dealAmount;
}

