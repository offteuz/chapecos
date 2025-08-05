package br.com.fiap.chapecos.adapter.inbound.controller;

import br.com.fiap.chapecos.adapter.inbound.dto.request.AddressRequestDTO;
import br.com.fiap.chapecos.adapter.inbound.dto.request.UserUpdateRequestDTO;
import br.com.fiap.chapecos.adapter.inbound.dto.response.UserResponseDTO;
import br.com.fiap.chapecos.domain.model.Address;
import br.com.fiap.chapecos.domain.model.Role;
import br.com.fiap.chapecos.domain.model.User;
import br.com.fiap.chapecos.domain.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    private static final String BASE_URL = "/api/user/v1";
    @Autowired
    private PasswordEncoder passwordEncoder;

    private static HttpHeaders headers;

    @BeforeAll
    static void setupHeaders() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer SEU_TOKEN_AQUI"); // ou dinamicamente, se preferir
    }

    @BeforeEach
    void setupDatabase() {
        // Garante um usuário para os testes
        if (userRepository.count() == 0) {
            Address address = new Address(
                    "04857-580",
                    "Rua A",
                    "123",
                    "Bairro",
                    "Cidade",
                    "SP",
                    "Brasil");
            User user = new User(
                    "lucas@email.com",
                    "Lucas Amaro",
                    passwordEncoder.encode("123456"),
                    address);
            user.setRole(Role.ADMIN);
            userRepository.save(user);
        }
    }

    @BeforeEach
    void setup() {
        userRepository.deleteAll();

        Address address = new Address(
                "12345-678",
                "Rua Teste",
                "123",
                "Centro",
                "Cidade",
                "SP",
                "Brasil");
        User user = new User(
                "teste@email.com",
                "Lucas Amaro",
                passwordEncoder.encode("senha123"),
                address);
        user.setRole(Role.ADMIN);

        userRepository.save(user);
    }

    @Test
    @Order(1)
    @DisplayName("Deve retornar todos os usuários")
    void shouldReturnAllUsers() {
        ResponseEntity<UserResponseDTO[]> response = restTemplate.getForEntity(
                BASE_URL + "/find-all",
                UserResponseDTO[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length >= 1);
    }

    @Test
    @Order(2)
    @DisplayName("Deve retornar usuário pelo ID")
    void shouldReturnUserById() {
        Long id = userRepository.findAll().get(0).getId();

        ResponseEntity<UserResponseDTO> response = restTemplate.getForEntity(
                BASE_URL + "/find-by-id/" + id,
                UserResponseDTO.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Lucas Amaro", response.getBody().userName());
    }

    @Test
    @Order(3)
    @DisplayName("Deve atualizar um usuário")
    void shouldUpdateUser() {
        Long id = userRepository.findAll().get(0).getId();

        UserUpdateRequestDTO updateDTO = new UserUpdateRequestDTO(
                "novo@email.com",
                "Novo Usuario",
                new AddressRequestDTO(
                        "11111-111",
                        "Nova Rua",
                        "321",
                        "Bairro Novo",
                        "Nova Cidade",
                        "RJ",
                        "Brasil"
        ));

        HttpEntity<UserUpdateRequestDTO> request = new HttpEntity<>(updateDTO);
        ResponseEntity<UserResponseDTO> response = restTemplate.exchange(
                BASE_URL + "/update/" + id,
                HttpMethod.PUT,
                request,
                UserResponseDTO.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(
                "Novo Usuario",
                response.getBody().userName());
    }

    @Test
    @Order(4)
    @DisplayName("Deve deletar um usuário")
    void shouldDeleteUser() {
        Long id = userRepository.findAll().get(0).getId();

        restTemplate.delete(BASE_URL + "/delete/" + id);

        assertFalse(userRepository.findById(id).isPresent());
    }

}
