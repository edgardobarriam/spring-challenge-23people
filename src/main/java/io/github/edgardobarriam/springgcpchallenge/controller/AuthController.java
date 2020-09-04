package io.github.edgardobarriam.springgcpchallenge.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@RestController
public class AuthController {
  
  private String jwtSecret = "mySuperSecureSecret";
  
  @PostMapping("hello")
  private String hello() {
    return "Hello from spring, you are authenticated";
  }
  
  @GetMapping("token")
  public String getToken() {
    return getJWTToken();
  }
  
  private String getJWTToken() {
    
    // This method should perform some sort of user/password validation, but considering this is an example, it is not
    // being validated.
    
    List<GrantedAuthority> grantedAuthorities = AuthorityUtils
      .commaSeparatedStringToAuthorityList("ROLE_USER");
    
    String token = Jwts
      .builder()
      .setId("gcpJWT")
      .claim("authorities",
        grantedAuthorities.stream()
          .map(GrantedAuthority::getAuthority)
          .collect(Collectors.toList()))
      .setIssuedAt(new Date(System.currentTimeMillis()))
      .setExpiration(new Date(System.currentTimeMillis() + 600000))
      .signWith(SignatureAlgorithm.HS512,
        jwtSecret.getBytes()).compact();
    
    return "Bearer " + token;
  }
}
