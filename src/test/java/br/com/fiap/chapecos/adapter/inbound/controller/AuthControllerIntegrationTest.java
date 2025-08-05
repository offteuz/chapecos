package br.com.fiap.chapecos.adapter.inbound.controller;

import br.com.fiap.chapecos.adapter.inbound.dto.request.AddressRequestDTO;
import br.com.fiap.chapecos.adapter.inbound.dto.request.UserLoginRequestDTO;
import br.com.fiap.chapecos.adapter.inbound.dto.request.UserRequestDTO;
import br.com.fiap.chapecos.domain.model.Address;
import br.com.fiap.chapecos.domain.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
//@AutoConfigureMockMvc
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
class AuthControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @MockBean
    private AuthenticationManager authenticationManager;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    // --- Testes para o endpoint /auth/v1/register ---
    @Test
    @Order(1)
    @DisplayName("Deve registrar um novo usuário com sucesso")
    void testRegister_NewUser_Success() throws Exception {
        UserRequestDTO requestDTO = new UserRequestDTO(
                "test@example.com",
                "Lucas Amaro",
                "P@ssword123",
                new Address(
                        "04543-000",
                        "Rua do Sucesso",
                        "123",
                        "Vila Olímpia",
                        "São Paulo",
                        "SP",
                        "Brasil"
                )
        );
        mockMvc.perform(post("/api/auth/v1/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isOk());
    }

    @Test
    @Order(2)
    @DisplayName("Não deve registrar usuário com email ou userName já existente")
    void testRegister_ExistingUser_BadRequest() throws Exception {

        // Primeiro, registra um usuário para que ele já exista
        UserRequestDTO existingUserDTO = new UserRequestDTO(
                "existing@example.com",
                "existinguser",
                "password123",
                new Address(
                        "04543-000",
                        "Rua do Sucesso",
                        "123",
                        "Vila Olímpia",
                        "São Paulo",
                        "SP",
                        "Brasil"
                )
        );
        userRepository.save(existingUserDTO.toUser());

        // Tenta registrar um novo usuário com o mesmo email
        UserRequestDTO duplicateEmailDTO = new UserRequestDTO(
                "existing@example.com",
                "anotheruser",
                "password123",
                new Address(
                "04543-000",
                "Rua do Sucesso",
                "123",
                "Vila Olímpia",
                "São Paulo",
                "SP",
                "Brasil"
            )
        );
        mockMvc.perform(post("/api/auth/v1/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(duplicateEmailDTO)))
                .andExpect(status().isBadRequest());

        // Tenta registrar um novo usuário com o mesmo userName
        UserRequestDTO duplicateUserNameDTO = new UserRequestDTO(
                "another@example.com",
                "existinguser",
                "password123",
                new Address(
                        "04543-000",
                        "Rua do Sucesso",
                        "123",
                        "Vila Olímpia",
                        "São Paulo",
                        "SP",
                        "Brasil"
                )
        );
        mockMvc.perform(post("/api/auth/v1/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(duplicateUserNameDTO)))
                .andExpect(status().isBadRequest());
    }



    // --- Testes para o endpoint /auth/v1/login ---
    @Test
    @Order(3)
    @DisplayName("Deve realizar o login com sucesso e retornar um token")
    void testLogin_ValidCredentials_Success() throws Exception {
        // Pré-condição: um usuário precisa existir no banco
        UserRequestDTO userDTO = new UserRequestDTO(
                "login@example.com",
                "loginuser",
                "password123",
                new Address(
                        "04543-000",
                        "Rua do Sucesso",
                        "123",
                        "Vila Olímpia",
                        "São Paulo",
                        "SP",
                        "Brasil"
                )
        );
        userRepository.save(userDTO.toUser());

        UserLoginRequestDTO loginDTO = new UserLoginRequestDTO("loginuser", "password123");

        // Simula o comportamento do AuthenticationManager para retornar um usuário autenticado
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(new UsernamePasswordAuthenticationToken(userRepository.findByUserName("loginuser").orElseThrow(), null));

        mockMvc.perform(post("/api/auth/v1/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").isString());
    }

    @Test
    @Order(4)
    @DisplayName("Não deve realizar o login com credenciais inválidas")
    void testLogin_InvalidCredentials_BadRequest() throws Exception {
        // Pré-condição: um usuário precisa existir no banco
        UserRequestDTO userDTO = new UserRequestDTO(
                "fail@example.com",
                "failuser",
                "password123",
                new Address(
                        "04543-000",
                        "Rua do Sucesso",
                        "123",
                        "Vila Olímpia",
                        "São Paulo",
                        "SP",
                        "Brasil"
                )
        );
        userRepository.save(userDTO.toUser());

        UserLoginRequestDTO loginDTO = new UserLoginRequestDTO("failuser", "wrongpassword");

        // Simula o AuthenticationManager lançando uma exceção para credenciais inválidas
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new BadCredentialsException("Invalid credentials"));

        mockMvc.perform(post("/api/auth/v1/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginDTO)))
                .andExpect(status().isUnauthorized()); // Use 401 Unauthorized para falha de login
    }
}
