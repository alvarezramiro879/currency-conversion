package com.currency.conversion.demo.application.port.output;

import reactor.core.publisher.Mono;

import java.math.BigDecimal;

public interface CurrencyConversionOutputPort {

  Mono<BigDecimal> getRate(String fromCurrency, String toCurrency);

  Mono<Boolean> isRateInCache(String cacheKey);

  Mono<Void> putRateInCache(String cacheKey, BigDecimal rate);

}
