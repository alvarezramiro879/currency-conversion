package com.currency.conversion.demo.infrastructure.persistence.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.math.BigInteger;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table("currency_conversion_rate")
public class CurrencyConversionRate {

  @Id
  private BigInteger Id;

  @Column(value = "from_currency")
  private String fromCurrency;
  @Column(value = "to_currency")
  private String toCurrency;
  @Column(value = "rate")
  private BigDecimal rate;

}
