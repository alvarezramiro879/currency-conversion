package com.currency.conversion.demo.adapter.dto.req;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
@AllArgsConstructor
public class CurrencyConversionRequest {

  @NotNull
  @DecimalMin(value = "0.0", inclusive = false, message = "Amount debe ser mayor a zero")
  private BigDecimal amount;

  @NotNull
  @Pattern(regexp = "^[A-Z]{3}$", message = "From debe tener el formato ISO 4217 de moneda")
  private String from;


  @NotNull
  @Pattern(regexp = "^[A-Z]{3}$", message = "To debe tener el formato ISO 4217 de moneda")
  private String to;
}
