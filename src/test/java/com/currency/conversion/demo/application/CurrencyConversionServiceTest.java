package com.currency.conversion.demo.application;

import com.currency.conversion.demo.adapter.dto.req.CurrencyConversionRequest;
import com.currency.conversion.demo.application.port.output.CurrencyConversionOutputPort;
import com.currency.conversion.demo.domain.exception.RateAlreadyConvertedException;
import com.currency.conversion.demo.domain.exception.RateNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CurrencyConversionServiceTest {

  @Mock
  private CurrencyConversionOutputPort currencyConversionOutputPort;

  @InjectMocks
  private CurrencyConversionService currencyConversionService;

  @Test
  public void testConvertCurrency_Success() {
    // Arrange
    CurrencyConversionRequest request = CurrencyConversionRequest.builder()
      .from("USD")
      .to("EUR")
      .amount(BigDecimal.valueOf(100.0))
      .build();

    String cacheKey = "USD-EUR";
    BigDecimal conversionRate = BigDecimal.valueOf(0.85);

    when(currencyConversionOutputPort.isRateInCache(cacheKey)).thenReturn(Mono.just(false));
    when(currencyConversionOutputPort.getRate(request.getFrom(), request.getTo())).thenReturn(Mono.just(conversionRate));

    StepVerifier.create(currencyConversionService.convertCurrency(request))
      .expectNextMatches(response -> {
        return response.getConvertedAmount().compareTo(BigDecimal.valueOf(85.0)) == 0 &&
          response.getRate().compareTo(conversionRate) == 0 &&
          response.getAmount().compareTo(request.getAmount()) == 0 &&
          response.getFrom().equals(request.getFrom()) &&
          response.getTo().equals(request.getTo());
      })
      .expectComplete()
      .verify();

    verify(currencyConversionOutputPort, times(1)).putRateInCache(cacheKey, conversionRate);
  }

  @Test
  public void testConvertCurrency_RateAlreadyConvertedException() {
    // Arrange
    CurrencyConversionRequest request = CurrencyConversionRequest.builder()
      .from("USD")
      .to("EUR")
      .amount(BigDecimal.valueOf(100.0))
      .build();

    String cacheKey = "USD-EUR";

    when(currencyConversionOutputPort.isRateInCache(cacheKey)).thenReturn(Mono.just(true));

    StepVerifier.create(currencyConversionService.convertCurrency(request))
      .expectErrorMatches(throwable -> throwable instanceof RateAlreadyConvertedException &&
        throwable.getMessage().equals("La tasa de cambio ya ha sido consultada, vuelva a intentarlo en unos minutos."))
      .verify();

    verify(currencyConversionOutputPort, never()).getRate(anyString(), anyString());
  }

  @Test
  public void testConvertCurrency_RateNotFoundException() {
    // Arrange
    CurrencyConversionRequest request = CurrencyConversionRequest.builder()
      .from("USD")
      .to("EUR")
      .amount(BigDecimal.valueOf(100.0))
      .build();

    String cacheKey = "USD-EUR";

    when(currencyConversionOutputPort.isRateInCache(cacheKey)).thenReturn(Mono.just(false));
    when(currencyConversionOutputPort.getRate(request.getFrom(), request.getTo())).thenReturn(Mono.empty());

    StepVerifier.create(currencyConversionService.convertCurrency(request))
      .expectErrorMatches(throwable -> throwable instanceof RateNotFoundException &&
        throwable.getMessage().equals("No se encontró la tasa de conversión para USD a EUR"))
      .verify();
  }
}