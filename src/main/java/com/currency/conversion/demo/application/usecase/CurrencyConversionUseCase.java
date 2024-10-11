package com.currency.conversion.demo.application.usecase;

import com.currency.conversion.demo.adapter.dto.req.CurrencyConversionRequest;
import com.currency.conversion.demo.adapter.dto.res.CurrencyConversionResponse;
import reactor.core.publisher.Mono;

public interface CurrencyConversionUseCase {

  Mono<CurrencyConversionResponse> convertCurrency(CurrencyConversionRequest convertCurrencyRequest);
}
