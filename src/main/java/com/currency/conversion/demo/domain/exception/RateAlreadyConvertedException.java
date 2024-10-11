package com.currency.conversion.demo.domain.exception;

public class RateAlreadyConvertedException extends RuntimeException {

  public RateAlreadyConvertedException(String message) {
    super(message);
  }
}
