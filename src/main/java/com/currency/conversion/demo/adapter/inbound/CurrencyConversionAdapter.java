package com.currency.conversion.demo.adapter.inbound;

import com.currency.conversion.demo.adapter.dto.req.CurrencyConversionRequest;
import com.currency.conversion.demo.adapter.dto.res.CurrencyConversionResponse;
import com.currency.conversion.demo.application.port.input.CurrencyConversionInputPort;
import com.currency.conversion.demo.application.usecase.CurrencyConversionUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CurrencyConversionAdapter implements CurrencyConversionInputPort {

  private final CurrencyConversionUseCase currencyConversionUseCase;
  @Override
  public Mono<CurrencyConversionResponse> convertCurrency(CurrencyConversionRequest convertCurrencyRequest) {
    return currencyConversionUseCase.convertCurrency(convertCurrencyRequest);
  }
}
