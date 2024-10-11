package com.currency.conversion.demo.adapter.outbound;

import com.currency.conversion.demo.application.port.output.CurrencyConversionOutputPort;
import com.currency.conversion.demo.infrastructure.persistence.entity.CurrencyConversionRate;
import com.currency.conversion.demo.infrastructure.persistence.repository.CurrencyConversionRateRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Component
@AllArgsConstructor
public class CurrencyConversionOutAdapter implements CurrencyConversionOutputPort {

  private final CacheManager cacheManager;
  private final CurrencyConversionRateRepository currencyConversionRateRepository;

  @Override
  public Mono<BigDecimal> getRate(String fromCurrency, String toCurrency) {
    return currencyConversionRateRepository.findByFromCurrencyAndToCurrency(fromCurrency, toCurrency)
      .map(CurrencyConversionRate::getRate);
  }

  @Override
  public Mono<Boolean> isRateInCache(String cacheKey) {
    var cache = cacheManager.getCache("conversionRates");
    return Mono.just(cache != null && cache.get(cacheKey) != null);
  }

  @Override
  public Mono<Void> putRateInCache(String cacheKey, BigDecimal rate) {
    var cache = cacheManager.getCache("conversionRates");
    if (cache != null) {
      cache.put(cacheKey, rate);
    }
    return Mono.empty();
  }
}
