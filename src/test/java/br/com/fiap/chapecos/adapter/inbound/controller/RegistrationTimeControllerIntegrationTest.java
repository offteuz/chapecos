package br.com.fiap.chapecos.adapter.inbound.controller;

import br.com.fiap.chapecos.adapter.inbound.dto.request.RegistrationTimeRequestDTO;
import br.com.fiap.chapecos.domain.model.Address;
import br.com.fiap.chapecos.domain.model.Establishment;
import br.com.fiap.chapecos.domain.model.KitchenType;
import br.com.fiap.chapecos.domain.model.User;
import br.com.fiap.chapecos.domain.repository.EstablishmentRepository;
import br.com.fiap.chapecos.domain.repository.UserRepository;
import br.com.fiap.chapecos.logic.ValidatorSchedules;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class RegistrationTimeControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EstablishmentRepository establishmentRepository;

    @MockBean
    private ValidatorSchedules validatorSchedules;

    private Long savedEstablishmentId;

    @BeforeEach
    void setup() {
        // Mock do serviço de validação para que ele não lance exceções
        doNothing().when(validatorSchedules).validate(any());

        Address address = new Address();
        address.setStreet("street");
        address.setCity("city");
        address.setState("state");
        address.setCountry("country");
        address.setPostalCode("postalCode");
        address.setNumber("number");
        address.setNeighborhood("neighborhood");
        address.setCountry("country");


        // Cria e salva um usuário no banco de dados de teste
        User user = new User();
        user.setUserName("testUser");
        user.setEmail("test@email.com");
        user.setPassword("password");
        user.setAddress(address);
        User savedUser = userRepository.save(user);

        // Cria e salva um estabelecimento no banco de dados de teste, associando-o ao usuário
        Establishment establishment = new Establishment();
        establishment.setName("Restaurante Teste");
        establishment.setKitchenType(KitchenType.ITALIANA);
        establishment.setCnpj("00.000.000/0001-00");
        establishment.setAddress(address);
        establishment.setUser(savedUser);

        Establishment savedEstablishment = establishmentRepository.save(establishment);
//        Establishment savedEstablishment = establishmentRepository.saveAndFlush(establishment);
        this.savedEstablishmentId = savedEstablishment.getId();
    }

    // --- Testes de Sucesso ---
    @Test
    @DisplayName("Deve criar um novo horário de registro com sucesso")
    void testCreateRegistrationTime_Success() throws Exception {
        // Arrange
        RegistrationTimeRequestDTO requestDTO = new RegistrationTimeRequestDTO(
                DayOfWeek.MONDAY,
                LocalTime.of(9, 0),
                LocalTime.of(18, 0),
                this.savedEstablishmentId
        );

        // Act & Assert
        mockMvc.perform(post("/api/registration-time/v1/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.dayOfWeek").value("MONDAY"));
    }

    // --- Testes de Falha ---
    @Test
    @DisplayName("Não deve criar um horário se o estabelecimento não existir")
    void testCreateRegistrationTime_EstablishmentNotFound() throws Exception {
        // Arrange
        RegistrationTimeRequestDTO requestDTO = new RegistrationTimeRequestDTO(
                DayOfWeek.MONDAY,
                LocalTime.of(9, 0),
                LocalTime.of(18, 0),
                999L // ID de estabelecimento que não existe
        );

        // Act & Assert
        mockMvc.perform(post("/api/registration-time/v1/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Não deve criar um horário com dados de entrada inválidos")
    void testCreateRegistrationTime_InvalidInput() throws Exception {
        // Arrange
        RegistrationTimeRequestDTO requestDTO = new RegistrationTimeRequestDTO(
                null, // DayOfWeek nulo
                LocalTime.of(9, 0),
                LocalTime.of(18, 0),
                this.savedEstablishmentId
        );

        // Act & Assert
        mockMvc.perform(post("/api/registration-time/v1/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isBadRequest());
    }
}