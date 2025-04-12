package com.example.red.social.redSocial_AO.Security.JWT;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    private static final String SECRET_KEY = "aSuperSecureKeyThatIsAtLeast32CharactersLong!!!";

    public String generateToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(getSingInKey() , SignatureAlgorithm.HS256)
                .compact();
    }

    public String getTokenFromRequest(HttpServletRequest request){
        final String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7); // corta "Bearer "
        }
        return null;
    }

    public boolean isTokenValid(String token){
        try{
            getClaims(token);
            return true;
        } catch (Exception e) {
            return false;

        }
    }

    public String getCorreoFromToken(String token){
        return getClaims(token).getSubject();
    }


    private Claims getClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSingInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractUsername(String token){
        return getClaims(token).getSubject();
    }

    public Key getSingInKey(){
        byte[] keyBytes = SECRET_KEY.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
