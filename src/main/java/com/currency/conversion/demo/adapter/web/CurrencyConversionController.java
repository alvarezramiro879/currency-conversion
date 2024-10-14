package com.currency.conversion.demo.adapter.web;

import com.currency.conversion.demo.adapter.dto.req.CurrencyConversionRequest;
import com.currency.conversion.demo.adapter.dto.res.CurrencyConversionResponse;
import com.currency.conversion.demo.application.port.input.CurrencyConversionInputPort;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/conversion")
@AllArgsConstructor
public class CurrencyConversionController {

  private final CurrencyConversionInputPort currencyConversionInputPort;

  @Operation(
    operationId = "convertCurrency",
    summary = "Convertir moneda",
    description = "Convierte un monto de una moneda a otra usando un tipo de cambio obtenido de una fuente externa."
  )
  @PostMapping
  Mono<CurrencyConversionResponse> convertCurrency(
    @Valid @RequestBody CurrencyConversionRequest convertCurrencyRequest
  ) {
    return currencyConversionInputPort.convertCurrency(convertCurrencyRequest);
  }
}
