package br.com.fiap.chapecos.adapter.inbound.controller;

import br.com.fiap.chapecos.adapter.inbound.dto.request.MenuRequestDTO;
import br.com.fiap.chapecos.adapter.inbound.dto.response.MenuResponseDTO;
import br.com.fiap.chapecos.domain.model.*;
import br.com.fiap.chapecos.domain.repository.EstablishmentRepository;
import br.com.fiap.chapecos.domain.repository.MenuRepository;
import br.com.fiap.chapecos.domain.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class MenuControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private EstablishmentRepository establishmentRepository;

    @Autowired
    private UserRepository userRepository; // Injetando o novo repository

    private Establishment establishment;
    private User user;

    @BeforeEach
    public void setup() {
        // 1. Cria e salva a entidade User, que é uma dependência de Establishment
        Address userAddress = new Address(
                "01001-000",
                "Rua do Teste",
                "123",
                "Bairro",
                "São Paulo",
                "SP",
                "Brasil");
        user = new User(
                null,
                "test@email.com",
                "testuser",
                "password123",
                userAddress,
                Role.USER,
                null,
                null);
        user = userRepository.save(user);

        // 2. Cria e salva a entidade Establishment, usando o User que acabou de ser persistido
        Address establishmentAddress = new Address(
                "04543-000",
                "Av. do Restaurante",
                "456",
                "Vila",
                "São Paulo",
                "SP",
                "Brasil");
        establishment = new Establishment(
                null,
                "Restaurante do Teste",
                "12.345.678/0001-90",
                KitchenType.ITALIANA,
                establishmentAddress,
                "America/Sao_Paulo",
                user,
                null,
                null,
                null);
        establishment = establishmentRepository.save(establishment);
    }

    @AfterEach
    public void cleanup() {
        // Limpeza dos dados na ordem correta para evitar erros de chave estrangeira
        menuRepository.deleteAll();
        establishmentRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    @Order(1)
    @DisplayName("Deve criar um menu")
    public void testCreateMenu_Success() throws Exception {
        MenuRequestDTO requestDTO = new MenuRequestDTO(MenuType.PRINCIPAL, establishment.getId());

        String responseContent = mockMvc.perform(post("/api/menu/v1/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.menuType.name").value(MenuType.PRINCIPAL.getName()))
                .andReturn().getResponse().getContentAsString();

        MenuResponseDTO responseDTO = objectMapper.readValue(responseContent, MenuResponseDTO.class);
        Optional<Menu> savedMenu = menuRepository.findById(responseDTO.idMenu());

        assertEquals(MenuType.PRINCIPAL, savedMenu.get().getMenuType());
        assertEquals(establishment.getId(), savedMenu.get().getEstablishment().getId());
    }

    @Test
    @Order(2)
    @DisplayName("Deve retornar todos os menus")
    public void testFindAllMenus_Success() throws Exception {
        menuRepository.save(new Menu(null, MenuType.PRINCIPAL, null, establishment, null));
        menuRepository.save(new Menu(null, MenuType.SOBREMESA, null, establishment, null));

        mockMvc.perform(get("/api/menu/v1/find-all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].menuType.name").value(MenuType.PRINCIPAL.getName()))
                .andExpect(jsonPath("$[1].menuType.name").value(MenuType.SOBREMESA.getName()));
    }

    @Test
    @Order(3)
    @DisplayName("Deve procurar o menu pelo ID")
    public void testFindMenuById_Success() throws Exception {
        Menu savedMenu = menuRepository.save(
                new Menu(
                    null,
                    MenuType.ENTRADA,
                    null,
                    establishment,
                    null)
        );

        mockMvc.perform(get("/api/menu/v1/find-by-id/{id}", savedMenu.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idMenu").value(savedMenu.getId()))
                .andExpect(jsonPath("$.menuType.name").value(MenuType.ENTRADA.getName()));
    }

    @Test
    @Order(4)
    @DisplayName("Deve falhar ao procurar menu pelo ID")
    public void testFindMenuById_NotFound() throws Exception {
        mockMvc.perform(get("/api/menu/v1/find-by-id/{id}", 999L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @Order(5)
    @DisplayName("Deve deletar um menu")
    public void testDeleteMenu_Success() throws Exception {
        Menu savedMenu = menuRepository.save(new Menu(null, MenuType.PRINCIPAL, null, establishment, null));

        mockMvc.perform(delete("/api/menu/v1/delete/{id}", savedMenu.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        assertEquals(Optional.empty(), menuRepository.findById(savedMenu.getId()));
    }
}