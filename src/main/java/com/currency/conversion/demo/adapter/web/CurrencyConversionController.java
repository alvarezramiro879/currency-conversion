package com.currency.conversion.demo.adapter.web;

import com.currency.conversion.demo.adapter.dto.req.CurrencyConversionRequest;
import com.currency.conversion.demo.adapter.dto.res.CurrencyConversionResponse;
import com.currency.conversion.demo.application.port.input.CurrencyConversionInputPort;
import com.currency.conversion.demo.domain.exception.RateAlreadyConvertedException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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
//    security = {
//      @SecurityRequirement(name = "bearerAuth")
//    }
  )
  @PostMapping
  Mono<CurrencyConversionResponse> convertCurrency(
    //  @NotNull @Parameter(name = "Authorization", description = "Token JWT para autenticación.", required = true, in = ParameterIn.HEADER) @RequestHeader(value = "Authorization", required = true) String authorization,
    @Valid @RequestBody CurrencyConversionRequest convertCurrencyRequest
  ) {
    return currencyConversionInputPort.convertCurrency(convertCurrencyRequest);
  }
}
