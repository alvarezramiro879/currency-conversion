package com.currency.conversion.demo.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
@AllArgsConstructor
public class ConversionErrorResponse {

  private int statusCode;
  private String message;
}
