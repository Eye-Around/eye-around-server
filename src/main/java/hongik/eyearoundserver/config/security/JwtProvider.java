package hongik.eyearoundserver.config.security;

import hongik.eyearoundserver.service.CustomUserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@RequiredArgsConstructor
@Component
@Slf4j
public class JwtProvider {

    @Value("${jwt.secret.key}")
    private String secretKey;
    // 만료시간 : 1시간
    private final long expire = 1000L * 60 * 60;

    private final CustomUserDetailsService userDetailsService;

    private Key getSignKey(String secretKey) {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String createToken(String email) {
        Date now = new Date();
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + expire))
                .claim("email", email)
                .signWith(getSignKey(secretKey))
                .compact();
    }

    // 권한 확인
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getEmail(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getEmail(String token) {
        token = token.split(" ")[1].trim();
        return Jwts.parserBuilder().setSigningKey(getSignKey(secretKey)).build().parseClaimsJws(token).getBody().getSubject();
    }

    public String getToken(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

    // 토큰 검증
    public boolean validateToken(String token) {
        try {
            token = token.split(" ")[1].trim();
            Claims claims = Jwts.parserBuilder().setSigningKey(getSignKey(secretKey)).build().parseClaimsJws(token).getBody();
            return claims.getExpiration().after(new Date());
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

}
