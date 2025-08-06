package br.com.fiap.chapecos.adapter.inbound.controller;

import br.com.fiap.chapecos.adapter.inbound.dto.request.AddressRequestDTO;
import br.com.fiap.chapecos.adapter.inbound.dto.request.EstablishmentRequestDTO;
import br.com.fiap.chapecos.adapter.inbound.dto.requestTest.EstablishmentRequestTestDTO;
import br.com.fiap.chapecos.domain.model.*;
import br.com.fiap.chapecos.domain.repository.EstablishmentRepository;
import br.com.fiap.chapecos.domain.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)
public class EstablishmentControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private EstablishmentRepository establishmentRepository;

    @Autowired
    private UserRepository userRepository;

    private User adminUser;
    private User regularUser;

    @BeforeEach
    public void setup() {
        // 1. Cria e salva um usuário ADMIN para testes de sucesso
        Address adminAddress = new Address(
                "01001-000",
                "Rua do Admin",
                "123",
                "Centro",
                "São Paulo",
                "SP",
                "Brasil");
        adminUser = new User(
                1L,
                "admin@email.com",
                "admin_user",
                "password123",
                adminAddress,
                Role.ADMIN,
                null,
                null
        );
        adminUser = userRepository.save(adminUser);

        // 2. Cria e salva um usuário regular para testar o cenário de falha
        Address regularAddress = new Address(
                "02002-000",
                "Rua do User",
                "456",
                "Vila",
                "São Paulo",
                "SP",
                "Brasil"
        );
        regularUser = new User(
                1L,
                "user@email.com",
                "regular_user",
                "password123",
                regularAddress,
                Role.USER,
                null,
                null);
        regularUser = userRepository.save(regularUser);
    }

    @AfterEach
    public void cleanup() {
        // Limpa o banco de dados
        establishmentRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    @Order(1)
    @DisplayName("Deve criar um novo estabelecimento")
    void testCreateEstablishment_WithAdminUser_Success() throws Exception {

        // Use o DTO de teste sem validações
        var requestDTO = new EstablishmentRequestTestDTO(
                "Restaurante Teste",
                "11.111.111/0001-11",
                KitchenType.ITALIANA,
                new AddressRequestDTO(
                        "04543-000",
                        "Av. Teste",
                        "10",
                        "Vila",
                        "São Paulo",
                        "SP",
                        "Brasil"),
                "America/Sao_Paulo",
                adminUser.getId()
        );

        mockMvc.perform(post("/api/establishment/v1/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Restaurante Teste"));
    }

    @Test
    @Order(2)
    @DisplayName("Deve falhar ao criar estabelecimento usuário comum")
    public void testCreateEstablishment_WithRegularUser_Failure() throws Exception {
        // Prepara o DTO de requisição com o usuário REGULAR
        EstablishmentRequestDTO requestDTO = new EstablishmentRequestDTO(
                "Restaurante do Teste User",
                "12.345.678/0001-93",
                KitchenType.JAPONESA,
                new AddressRequestDTO(
                        "04543-000",
                        "Av. Teste 2",
                        "200",
                        "Vila",
                        "São Paulo",
                        "SP",
                        "Brasil"),
                "America/Sao_Paulo",
                regularUser.getId()
        );

        // Executa a requisição e verifica se retorna erro 400 (Bad Request),
        // que deve ser mapeado por um handler de exceção (UserNotAdminException)
        mockMvc.perform(post("/api/establishment/v1/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isBadRequest()); // Assumindo que você tem um handler para UserNotAdminException
    }

    @Test
    @Order(3)
    @DisplayName("Deve retornar todos os estabelecimentos")
    public void testFindAllEstablishments_Success() throws Exception {
        // Prepara dados no banco de dados para o teste
        Establishment establishment1 = new Establishment(
                null,
                "E-1",
                "11.111.111/0001-11",
                KitchenType.CHINESA,
                new Address(
                        "04543-000",
                        "Av. Teste Um",
                        "1006",
                        "Vila",
                        "São Paulo",
                        "SP",
                        "Brasil"),
                "America/Sao_Paulo",
                adminUser,
                null,
                null,
                null);
        Establishment establishment2 = new Establishment(
                null,
                "E-2",
                "22.222.222/0001-22",
                KitchenType.MEXICANA,
                new Address(
                        "04543-000",
                        "Av. Teste Dois",
                        "1260",
                        "Vila",
                        "São Paulo",
                        "SP",
                        "Brasil"),
                "America/Sao_Paulo",
                adminUser,
                null,
                null,
                null
        );
        establishmentRepository.save(establishment1);
        establishmentRepository.save(establishment2);

        // Executa a requisição e verifica se a lista tem 2 itens
        mockMvc.perform(get("/api/establishment/v1/find-all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name").value("E-1"))
                .andExpect(jsonPath("$[1].name").value("E-2"));
    }

    @Test
    @Order(4)
    @DisplayName("Deve retornar o estabelecimento por ID")
    public void testFindEstablishmentById_Success() throws Exception {
        Establishment savedEstablishment = establishmentRepository.save(
                new Establishment(
                        null,
                        "Restaurante de Busca",
                        "12.345.678/0001-94",
                        KitchenType.ITALIANA,
                        new Address(
                                "04543-000",
                                "Av. Teste",
                                "100",
                                "Vila",
                                "São Paulo",
                                "SP",
                                "Brasil"),
                        "America/Sao_Paulo",
                        adminUser,
                        null,
                        null,
                        null)
        );

        mockMvc.perform(get("/api/establishment/v1/find-by-id/{id}", savedEstablishment.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idEstablishment").value(savedEstablishment.getId()))
                .andExpect(jsonPath("$.name").value("Restaurante de Busca"));
    }

    @Test
    @Order(5)
    @DisplayName("Deve falhar ao retornar estabelecimento por ID")
    public void testFindEstablishmentById_NotFound() throws Exception {
        mockMvc.perform(get("/api/establishment/v1/find-by-id/{id}", 999L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @Order(6)
    @DisplayName("Deve deletar um estabelecimento")
    public void testDeleteEstablishment_Success() throws Exception {
        Establishment savedEstablishment = establishmentRepository.save(
                new Establishment(
                        null,
                        "Restaurante para Deletar",
                        "12.345.678/0001-95",
                        KitchenType.ITALIANA,
                        null,
                        "America/Sao_Paulo",
                        adminUser,
                        null,
                        null,
                        null)
        );

        mockMvc.perform(delete("/api/establishment/v1/delete/{id}", savedEstablishment.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        assertEquals(Optional.empty(), establishmentRepository.findById(savedEstablishment.getId()));
    }
}
