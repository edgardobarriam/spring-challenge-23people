package util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public final class JWTUtils {
  
  private static final String jwtSecret = "mySuperSecureSecret";
  public static final String AUTHENTICATION_HEADER = "Authorization";
  public static final String AUTHENTICATION_PREFIX = "Bearer ";
  
  public static String getJWTToken() {
    
    /* This method should perform some sort of user/password validation, but considering this is an example, credentials
     are not being validated. */
  
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
  
    return AUTHENTICATION_PREFIX + token;
  }
  
  public static Claims validateToken(String jwtToken) {
    return Jwts.parser().setSigningKey(JWTUtils.jwtSecret.getBytes()).parseClaimsJws(jwtToken).getBody();
  }
}
