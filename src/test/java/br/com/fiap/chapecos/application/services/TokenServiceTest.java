package br.com.fiap.chapecos.application.services;

import br.com.fiap.chapecos.application.service.TokenService;
import br.com.fiap.chapecos.domain.model.User;
import com.auth0.jwt.exceptions.JWTDecodeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

public class TokenServiceTest {

    private TokenService tokenService;

    @BeforeEach
    public void setup() throws Exception {
        tokenService = new TokenService("token");

        Field secretField = TokenService.class.getDeclaredField("secret");
        secretField.setAccessible(true);
        secretField.set(tokenService, "my-secret-key");
    }

    @Test
    void shouldGenerateAndValidateTokenSuccessfully() {
        User user = new User();
        user.setUserName("testuser");

        String token = tokenService.tokengenerate(user);
        assertNotNull(token);
        assertFalse(token.isEmpty());

        String subject = tokenService.validateToken(token);
        assertEquals("testuser", subject);
    }

    @Test
    void shouldThrowExceptionWhenTokenIsInvalid() {

        assertThrows(JWTDecodeException.class, () -> {
            tokenService.validateToken("invalid.token.value");
        });
    }
}
