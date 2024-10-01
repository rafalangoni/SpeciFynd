package med.voll.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import med.voll.api.domain.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    /* Generating and validating token JWT using auth0 library.
    Also, implementing expiry date for token
    * */

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(User user){
        try {
            var algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("API Voll.med")
                    .withSubject(user.getLogin())
                    .withExpiresAt(expiryDate())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception){
           throw new RuntimeException("Error generating JWT ", exception);
        }
    }

    public String validateToken(String tokenJWT){
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("API Voll.med")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception){
            throw new RuntimeException("Invalid or expiry token JWT.");
        }
    }

    private Instant expiryDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:30"));
    }

}
