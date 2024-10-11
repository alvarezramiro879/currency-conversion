package com.currency.conversion.demo.adapter.dto.req;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
public class CurrencyConversionRequest {

  private BigDecimal amount;

  private String from;

  private String to;
}
