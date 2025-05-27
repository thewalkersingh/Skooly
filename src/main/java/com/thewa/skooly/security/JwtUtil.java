package com.thewa.skooly.security;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
   private final SecretKey SECRET_KEY;
   private final long EXPIRATION_TIME;
   
   public JwtUtil(@Value("${jwt.secret}") String jwtSecret,
		   @Value("${jwt.expiration}") long jwtExpirationMs) {
	  this.SECRET_KEY = Keys.hmacShaKeyFor(jwtSecret.getBytes()); // Secure 64-bit key
	  this.EXPIRATION_TIME = jwtExpirationMs;
   }
   
   // Generate JWT Token with Username & Role
   public String generateJwtToken(String username, String role) {
	  Map<String, Object> claims = new HashMap<>();
	  claims.put("role", role);
	  
	  return Jwts.builder()
				 .claims(claims)
				 .subject(username)
				 .issuedAt(new Date())
				 .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				 .signWith(SECRET_KEY, Jwts.SIG.HS256) // Uses latest JJWT signing method
				 .compact();
   }
   
   // Extract Username from Token
   public String extractUsername(String token) {
	  return Jwts.parser()
				 .verifyWith(SECRET_KEY) // Updated verification method
				 .build()
				 .parseSignedClaims(token)
				 .getPayload()
				 .getSubject();
   }
   
   // Extract Role from Token
   public String extractRole(String token) {
	  return (String) Jwts.parser()
						  .verifyWith(SECRET_KEY)
						  .build()
						  .parseSignedClaims(token)
						  .getPayload()
						  .get("role");
   }
   
   // Validate JWT Token
   public boolean validateJwtToken(String token) {
	  try{
		 Jwts.parser()
			 .verifyWith(SECRET_KEY)
			 .build()
			 .parseSignedClaims(token);
		 return true;
	  } catch(JwtException | IllegalArgumentException e){
		 return false;
	  }
   }
}