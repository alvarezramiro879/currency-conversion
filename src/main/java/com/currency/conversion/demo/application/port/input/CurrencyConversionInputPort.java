package com.currency.conversion.demo.application.port.input;

import com.currency.conversion.demo.adapter.dto.req.CurrencyConversionRequest;
import com.currency.conversion.demo.adapter.dto.res.CurrencyConversionResponse;
import reactor.core.publisher.Mono;

public interface CurrencyConversionInputPort {

  Mono<CurrencyConversionResponse> convertCurrency(CurrencyConversionRequest convertCurrencyRequest);
}
