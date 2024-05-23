package com.josepdevs.Domain.service;

import java.security.Key;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import io.jsonwebtoken.security.Keys;

@Service
public class JwtTokenGeneratorService {
	  
	@Value("${jwt.token.expire-length}")
	private long jwtExpirationMs;
	
    public String generateToken(String secretKey) {
        // Convert the secret key string to bytes
        byte[] keyBytes = Base64.getDecoder().decode(secretKey);

        // Generate the Key instance
        Key signingKey = Keys.hmacShaKeyFor(keyBytes);
        
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        calendar.add(Calendar.MINUTE, 15); // 15 minutes and the token will expire if does not receive a refresh token
        Date expirationDate = calendar.getTime();
        this.issuerID = Files.readAllLines(Paths.get("src", "main", "conf", "issuer-id.txt")).get(0);

        
        // Create the JWT token
        String token = JWT.create().withSubject("")
                .withExpiresAt(expirationDate)
                .withIssuer(this.issuerID)
                .withIssuedAt(now)
                .withNotBefore(now)
                .withClaim("userFingerprint", userFingerprintHash)
                .withHeader(headerClaims)
                .sign(Algorithm.HMAC256(this.keyHMAC));

        return token;
    }
}
