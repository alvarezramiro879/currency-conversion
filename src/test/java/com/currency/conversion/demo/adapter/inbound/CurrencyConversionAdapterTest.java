package com.currency.conversion.demo.adapter.inbound;

import com.currency.conversion.demo.adapter.dto.req.CurrencyConversionRequest;
import com.currency.conversion.demo.adapter.dto.res.CurrencyConversionResponse;
import com.currency.conversion.demo.application.usecase.CurrencyConversionUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CurrencyConversionAdapterTest {

  @InjectMocks
  private CurrencyConversionAdapter currencyConversionAdapter;

  @Mock
  private CurrencyConversionUseCase currencyConversionUseCase;


  @Test
  public void testConvertCurrency_Success() {
    CurrencyConversionRequest request = CurrencyConversionRequest.builder()
      .from("USD")
      .to("PEN")
      .amount(BigDecimal.valueOf(1))
      .build();

    CurrencyConversionResponse expectedResponse = CurrencyConversionResponse.builder()
      .convertedAmount(BigDecimal.valueOf(3.8))
      .build();

    when(currencyConversionUseCase.convertCurrency(request)).thenReturn(Mono.just(expectedResponse));

    StepVerifier.create(currencyConversionAdapter.convertCurrency(request))
      .expectNext(expectedResponse)
      .expectComplete()
      .verify();
  }
}