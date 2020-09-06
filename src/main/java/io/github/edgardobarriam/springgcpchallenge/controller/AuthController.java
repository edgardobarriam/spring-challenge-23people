package io.github.edgardobarriam.springgcpchallenge.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import io.github.edgardobarriam.springgcpchallenge.util.JWTUtils;


@RestController
public class AuthController {
  
  @GetMapping("token")
  public String getToken() {
    return JWTUtils.getJWTToken();
  }
}
