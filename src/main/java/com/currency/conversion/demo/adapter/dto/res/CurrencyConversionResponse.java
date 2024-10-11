package com.currency.conversion.demo.adapter.dto.res;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Builder
@Setter
public class CurrencyConversionResponse {
  private BigDecimal amount;

  private BigDecimal convertedAmount;

  private String from;

  private String to;

  private BigDecimal rate;
}
