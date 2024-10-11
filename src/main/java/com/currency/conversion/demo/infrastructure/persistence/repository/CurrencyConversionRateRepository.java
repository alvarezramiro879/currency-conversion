package com.currency.conversion.demo.infrastructure.persistence.repository;

import com.currency.conversion.demo.infrastructure.persistence.entity.CurrencyConversionRate;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

import java.math.BigInteger;

public interface CurrencyConversionRateRepository extends R2dbcRepository<CurrencyConversionRate, String> {

  //@Query("SELECT * FROM currency_conversion_rate WHERE fromCurrency = :fromCurrency AND toCurrency = :toCurrency")
  Mono<CurrencyConversionRate> findByFromCurrencyAndToCurrency(String fromCurrency, String toCurrency);

  Mono<CurrencyConversionRate> findById(Integer id);


}
