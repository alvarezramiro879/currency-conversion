package com.currency.conversion.demo.adapter.web;

import com.currency.conversion.demo.domain.exception.ConversionErrorResponse;
import com.currency.conversion.demo.domain.exception.RateAlreadyConvertedException;
import com.currency.conversion.demo.domain.exception.RateNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
public class GlobalExceptionHandler {


  // Maneja RateAlreadyExistsException
  @ExceptionHandler(RateAlreadyConvertedException.class)
  public ResponseEntity<Map<String, Object>> handleRateAlreadyExistsException(RateAlreadyConvertedException ex) {

    Map<String, Object> response = new HashMap<>();
    response.put("status", HttpStatus.CONFLICT.value());
    response.put("message", ex.getMessage());
    return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
  }

  // Maneja RateNotFoundException
  @ExceptionHandler(RateNotFoundException.class)
  public ResponseEntity<Map<String, Object>> handleRateNotFoundException(RateNotFoundException ex) {

    Map<String, Object> response = new HashMap<>();
    response.put("status", HttpStatus.NOT_FOUND.value());
    response.put("message", ex.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
  }

  // Maneja otras excepciones no controladas
  @ExceptionHandler(Exception.class)
  public ResponseEntity<Map<String, Object>> handleGenericException(Exception ex) {

    Map<String, Object> response = new HashMap<>();
    response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
    response.put("message", ex.getMessage());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
  }
}
