package com.currency.conversion.demo.infrastructure.persistence.repository;

import com.currency.conversion.demo.infrastructure.persistence.entity.CurrencyConversionRate;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

import java.math.BigInteger;

public interface CurrencyConversionRateRepository extends R2dbcRepository<CurrencyConversionRate, String> {

  Mono<CurrencyConversionRate> findByFromCurrencyAndToCurrency(String fromCurrency, String toCurrency);

  Mono<CurrencyConversionRate> findById(Integer id);


}
