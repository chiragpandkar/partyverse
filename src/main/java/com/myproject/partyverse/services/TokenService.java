package com.myproject.partyverse.services;

import com.myproject.partyverse.config.ConfigProps;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.hibernate.engine.transaction.jta.platform.internal.SynchronizationRegistryBasedSynchronizationStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {


  private Logger logger = LoggerFactory.getLogger(TokenService.class);



  public String generateToken(Long userId){
    Date issuedDate = new Date(System.currentTimeMillis());
    Date expirationDate = new Date(System.currentTimeMillis() + ConfigProps.JWT_TOKEN_LIFE_TIME);
    return Jwts.builder().claim("userId", userId)
        .setIssuedAt(issuedDate)
        .setExpiration(expirationDate)
        .setIssuer(ConfigProps.JWT_ISSUER)
      .signWith(SignatureAlgorithm.HS256, ConfigProps.JWT_SECRET_KEY)
      .compact();
  }

  public Long validateToken(String token){
    try{
      Claims claims = Jwts.parser()
              .setSigningKey(ConfigProps.JWT_SECRET_KEY)
              .parseClaimsJws(token)
              .getBody();

      return Long.parseLong(claims.get("userId").toString());
    } catch(Exception e){
      return null;
    }
  }
}
