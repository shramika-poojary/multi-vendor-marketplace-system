package com.data.security;

import java.security.Key;
import java.util.Date;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	
	private final String secret = "mysignatureisshopcircle12mysignatureisshopcircle12"; //should be 32+ char
	
	public Key getKey() {
		return Keys.hmacShaKeyFor(secret.getBytes());
	}
	
//	public String generateToken(UserDetails userDetails) {
//        return Jwts.builder()
//                .setSubject(userDetails.getUsername()) // email
//                .claim("roles", userDetails.getAuthorities())
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hr
//                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
//                .compact();
//    }
	
	 public String generateToken(UserDetails userDetails) {
	        return Jwts.builder()
	                .setSubject(userDetails.getUsername()) // email
	                .setIssuedAt(new Date())
	                .setExpiration(
	                        new Date(System.currentTimeMillis() + 1000 * 60 * 60))
	                .signWith(getKey())
	                .compact();
	  }
	 
	 public String extractUsername(String token) {
	        return Jwts.parserBuilder()
	                .setSigningKey(getKey())
	                .build()
	                .parseClaimsJws(token)
	                .getBody()
	                .getSubject();
	 }
	 
//    public String extractToken(String token) {
//    	return Jwts.parserBuilder()
//    			   .setSigningKey(getKey())
//    			   .build()
//    			   .parseClaimsJws(token)
//    			   .getBody()
//    			   .getSubject();
//    }
//    
	 public boolean validateToken(String token, UserDetails userDetails) {
	        String username = extractUsername(token);
	        return username.equals(userDetails.getUsername())
	                && !isTokenExpired(token);
	    }
	 
	 private boolean isTokenExpired(String token) {
	        Date expiry = Jwts.parserBuilder()
	                .setSigningKey(getKey())
	                .build()
	                .parseClaimsJws(token)
	                .getBody()
	                .getExpiration();
	        return expiry.before(new Date());
	    }
}
