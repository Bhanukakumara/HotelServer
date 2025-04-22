package edu.bks.HotelServer.utill;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

@Component
public class JwtUtil {
    private String generateToken(Map<String, Objects> extraClaims, UserDetails details){
        return Jwts.builder().setClaims(extraClaims)
                .setSubject(details.getUsername()).
                setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*60*24))
                .signWith(getSigninKey(), SignatureAlgorithm.ES256).compact();
    }

    private Key getSigninKey(){
        byte[] keyBytes = Decoders.BASE64.decode("1bb2edb4057745a98527a3d7c7172b58");
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
