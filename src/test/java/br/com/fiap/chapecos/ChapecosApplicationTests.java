package br.com.fiap.chapecos;

import br.com.fiap.chapecos.infrastructure.config.security.TestSecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(
		classes = {
				ChapecosApplication.class,
				TestSecurityConfig.class
		}
)
@ActiveProfiles("test")
class ChapecosApplicationTests {

	@Test
	void contextLoads() {
	}

}
