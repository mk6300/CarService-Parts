package carservice.carserviceparts.service.Impl;

import carservice.carserviceparts.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.List;

@Service
public class JwtServiceImpl implements JwtService {

    private final String secretKey;


    public JwtServiceImpl(@Value("${jwt.secret}") String secretKey) {
        this.secretKey = secretKey;
    }
    @Override
    public UserDetails extractUserDetails(String jwtToken) {
        Claims claims = extractClaims(jwtToken);
        String userId = claims.getSubject();
        List<String> roles = getRolesFromClaims(claims);


        return new User(userId, "", roles.stream().map(SimpleGrantedAuthority::new).toList());
    }

    private static List<String> getRolesFromClaims(Claims claims) {
        return claims.get("roles", List.class);
    }
    private Claims extractClaims(String jwtToken) {
        return Jwts.parserBuilder()
                .setSigningKey(GetSecretKey())
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();
    }
    private Key GetSecretKey() {
        byte[] decodedKey = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(decodedKey);
    }
}
