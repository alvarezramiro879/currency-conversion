package com.currency.conversion.demo.domain.exception;

public class RateNotFoundException extends RuntimeException{

  public RateNotFoundException(String message) {
    super(message);
  }
}
