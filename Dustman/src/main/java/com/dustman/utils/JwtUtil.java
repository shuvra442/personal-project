package com.dustman.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;

import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {

//    @Value("JWT.SECRET")
    @Value("${jwt.secret}")
    private String secret;//="QWERTYUIOPLKJHGFDSAZXCVBNMQWERTYUIOPLKJHGFDSAZXCVBNM";
//    private String encodedSecret =Base64.getEncoder().encodeToString(secret.getBytes());

    public String extractUserName(String token){
        return extractClaims(token,Claims::getSubject);
    }

    private Date extractExpireDate(String token){
        return extractClaims(token,Claims::getExpiration);
    }

    public boolean isTokenExpire(String token){
        Date tokenExpaireDate=extractExpireDate(token);
        return tokenExpaireDate.before(new Date());
    }

    public boolean isValidToken(String token,String userName){
        final String extractedUsername = extractUserName(token);
        return (extractedUsername.equals(userName) && !isTokenExpire(token));
    }

    private  <T> T extractClaims(String token,Function<Claims,T> claimsTFunction ){
        final  Claims claims=extractAllClaims(token);
        return claimsTFunction.apply(claims);
    }


    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secret.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }



    public String generateJWT(UserDetails userDetails){
        SecretKeySpec secretKeySpec=new SecretKeySpec(secret.getBytes(), SignatureAlgorithm.HS256.getJcaName());

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60))
                .signWith(secretKeySpec,SignatureAlgorithm.HS256)
                .compact();

    }


}
