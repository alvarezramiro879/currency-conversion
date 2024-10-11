package com.currency.conversion.demo.application.port.output;

import com.currency.conversion.demo.adapter.dto.req.CurrencyConversionRequest;
import com.currency.conversion.demo.adapter.dto.res.CurrencyConversionResponse;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

public interface CurrencyConversionOutputPort {

  Mono<BigDecimal> getRate(String fromCurrency, String toCurrency);

  Mono<Boolean> isRateInCache(String cacheKey);

  Mono<Void> putRateInCache(String cacheKey, BigDecimal rate);

}
