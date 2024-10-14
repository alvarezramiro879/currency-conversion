package com.currency.conversion.demo.application;

import com.currency.conversion.demo.adapter.dto.req.CurrencyConversionRequest;
import com.currency.conversion.demo.adapter.dto.res.CurrencyConversionResponse;
import com.currency.conversion.demo.application.port.output.CurrencyConversionOutputPort;
import com.currency.conversion.demo.application.usecase.CurrencyConversionUseCase;
import com.currency.conversion.demo.domain.exception.RateAlreadyConvertedException;
import com.currency.conversion.demo.domain.exception.RateNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class CurrencyConversionService implements CurrencyConversionUseCase {

  private final CurrencyConversionOutputPort currencyConversionOutputPort;
  @Override
  public Mono<CurrencyConversionResponse> convertCurrency(CurrencyConversionRequest convertCurrencyRequest) {
    String cacheKey = convertCurrencyRequest.getFrom() + "-" + convertCurrencyRequest.getTo();

    return currencyConversionOutputPort.isRateInCache(cacheKey)
      .flatMap(isInCache -> {
        if (isInCache) {
          return Mono.error(new RateAlreadyConvertedException("La tasa de cambio ya ha sido consultada, vuelva a intentarlo en unos minutos."));
        }
        return currencyConversionOutputPort.getRate(convertCurrencyRequest.getFrom(), convertCurrencyRequest.getTo())
          .map(currencyConversionRate -> CurrencyConversionResponse.builder()
            .convertedAmount(convertCurrencyRequest.getAmount().multiply(currencyConversionRate))
            .rate(currencyConversionRate)
            .amount(convertCurrencyRequest.getAmount())
            .from(convertCurrencyRequest.getFrom())
            .to(convertCurrencyRequest.getTo())
            .build())
          .switchIfEmpty(Mono.error(new RateNotFoundException("No se encontró la tasa de conversión para " + convertCurrencyRequest.getFrom() + " a " + convertCurrencyRequest.getTo())))
          .doOnNext(currencyConversionResponse -> {
            currencyConversionOutputPort.putRateInCache(cacheKey, currencyConversionResponse.getRate());
          });
      });
  }
}
