package yarema.project.toystore.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import yarema.project.toystore.entity.UserDetailsImpl;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenUtils {
    public String generateJwtToken(Authentication authentication) {
        var principal = (UserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(principal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+10000000))
                .signWith(Keys.secretKeyFor(SignatureAlgorithm.HS512))
                .compact();
    }

    public String getUsernameFromJwtToken(String token) {
        return Jwts.parser()
                .setSigningKey(Keys.secretKeyFor(SignatureAlgorithm.HS512))
                .parseClaimsJwt(token)
                .getBody()
                .getSubject();
    }

    public boolean validate(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(Keys.secretKeyFor(SignatureAlgorithm.HS512))
                    .parseClaimsJwt(token);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
