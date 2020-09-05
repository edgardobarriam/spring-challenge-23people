package io.github.edgardobarriam.springgcpchallenge.security;

import io.jsonwebtoken.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import io.github.edgardobarriam.springgcpchallenge.util.JWTUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class JWTAuthorizationFilter extends OncePerRequestFilter {
  
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
    throws ServletException, IOException {
    try {
      if (existsJWTToken(request)) {
        String tokenFromHeader = request.getHeader(JWTUtils.AUTHENTICATION_HEADER).replace(JWTUtils.AUTHENTICATION_PREFIX, "");
        Claims claims = JWTUtils.validateToken(tokenFromHeader);
        
        if (claims.get("authorities") != null) {
          setUpSpringAuthentication(claims);
        } else {
          SecurityContextHolder.clearContext();
        }
        
      } else {
        SecurityContextHolder.clearContext();
      }
      
      filterChain.doFilter(request, response);
      
    } catch (JwtException exception) {
      response.setStatus(HttpServletResponse.SC_FORBIDDEN);
      response.sendError(HttpServletResponse.SC_FORBIDDEN, exception.getMessage());
    }
  }
  
  private void setUpSpringAuthentication(Claims claims) {
    @SuppressWarnings("unchecked")
    List<String> authorities = (List) claims.get("authorities");
    
    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(claims.getSubject(), null,
      authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
    SecurityContextHolder.getContext().setAuthentication(auth);
  }
  
  private boolean existsJWTToken(HttpServletRequest request) {
    String authenticationHeader = request.getHeader(JWTUtils.AUTHENTICATION_HEADER);
    return authenticationHeader != null && authenticationHeader.startsWith(JWTUtils.AUTHENTICATION_PREFIX);
  }
}
