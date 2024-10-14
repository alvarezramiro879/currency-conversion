package com.currency.conversion.demo.adapter.web;

import com.currency.conversion.demo.adapter.dto.req.AuthRequest;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

  @Value("${jwt.secret}")
  private String secretKey;

  @PostMapping("/login")
  public String authenticate(@RequestBody AuthRequest authRequest) {

    Map<String, Object> claims = new HashMap<>();
    return Jwts.builder()
      .setClaims(claims)
      .setSubject(authRequest.getUser())
      .setIssuedAt(new Date(System.currentTimeMillis()))
      .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
      .signWith(SignatureAlgorithm.HS256, secretKey)
      .compact();
  }
}
