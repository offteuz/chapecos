package br.com.fiap.chapecos.adapter.inbound.controller;

import br.com.fiap.chapecos.adapter.inbound.dto.request.ItemRequestDTO;
import br.com.fiap.chapecos.adapter.inbound.dto.response.ItemResponseDTO;
import br.com.fiap.chapecos.domain.model.*;
import br.com.fiap.chapecos.domain.repository.EstablishmentRepository;
import br.com.fiap.chapecos.domain.repository.ItemRepository;
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

import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ItemControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private EstablishmentRepository establishmentRepository;

    @Autowired
    private UserRepository userRepository;

    private User user;
    private Establishment establishment;
    private Menu menu;

    @BeforeEach
    public void setup() {
        // 1. Cria e salva a hierarquia de dependências: User -> Establishment -> Menu
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
                "test_item@email.com",
                "testuser_item",
                "password123",
                userAddress,
                Role.USER,
                null,
                null);
        user = userRepository.save(user);

        Address establishmentAddress = new Address(
                "04543-000",
                "Av. do Restaurante",
                "456",
                "Vila",
                "São Paulo",
                "SP",
                "Brasil"
        );
        establishment = new Establishment(
                null,
                "Restaurante do Teste Item",
                "12.345.678/0001-91",
                KitchenType.ITALIANA,
                establishmentAddress,
                "America/Sao_Paulo",
                user,
                null,
                null,
                null
        );
        establishment = establishmentRepository.save(establishment);

        menu = new Menu(
                null,
                MenuType.PRINCIPAL,
                null,
                establishment,
                null);
        menu = menuRepository.save(menu);
    }

    @AfterEach
    public void cleanup() {
        // Limpa todas as tabelas na ordem correta (filho -> pai)
        itemRepository.deleteAll();
        menuRepository.deleteAll();
        establishmentRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    @Order(1)
    @DisplayName("Deve criar um item")
    public void testCreateItem_Success() throws Exception {
        // Prepara o DTO de requisição, usando o ID do menu criado no setup()
        ItemRequestDTO requestDTO = new ItemRequestDTO(
                "Pizza Margherita",
                "Mussarela, tomate e manjericão",
                55.00,
                LocalConsumption.RESTAURANTE,
                menu.getId()
        );

        // Executa a requisição HTTP POST e verifica a resposta
        String responseContent = mockMvc.perform(post("/api/item/v1/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Pizza Margherita"))
                .andExpect(jsonPath("$.price").value(55.00))
                .andReturn().getResponse().getContentAsString();

        // Valida se a entidade foi salva corretamente no banco de dados
        ItemResponseDTO responseDTO = objectMapper.readValue(responseContent, ItemResponseDTO.class);
        Optional<Item> savedItem = itemRepository.findById(responseDTO.idItem());

        assertEquals("Pizza Margherita", savedItem.get().getName());
        assertEquals(menu.getId(), savedItem.get().getMenu().getId());
    }

    @Test
    @Order(2)
    @DisplayName("Deve retornar todos os itens")
    public void testFindAllItems_Success() throws Exception {
        // Cria alguns itens para o teste
        itemRepository.save(
                new Item(
                        null,
                        "Bife Ancho",
                        "Com batatas fritas",
                        89.90, LocalConsumption.RESTAURANTE,
                        menu,
                        null));
        itemRepository.save(
                new Item(
                        null,
                        "Risoto de cogumelos",
                        "Arroz arbóreo com mix de cogumelos",
                        65.50,
                        LocalConsumption.RESTAURANTE,
                        menu,
                        null));

        // Executa a requisição HTTP GET e verifica a lista de itens
        mockMvc.perform(get("/api/item/v1/find-all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name").value("Bife Ancho"))
                .andExpect(jsonPath("$[1].name").value("Risoto de cogumelos"));
    }

    @Test
    @Order(3)
    @DisplayName("Deve retornar um item pelo ID")
    public void testFindItemById_Success() throws Exception {
        // Salva um item para o teste
        Item savedItem = itemRepository.save(
                new Item(
                        null,
                        "Lasanha Bolonhesa",
                        "Massa fresca e molho bolonhesa",
                        45.00,
                        LocalConsumption.RESTAURANTE,
                        menu,
                        null));

        // Busca o item pelo ID e verifica os detalhes
        mockMvc.perform(get("/api/item/v1/find-by-id/{id}", savedItem.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idItem").value(savedItem.getId()))
                .andExpect(jsonPath("$.name").value("Lasanha Bolonhesa"))
                .andExpect(jsonPath("$.menu.idMenu").value(menu.getId()));
    }

    @Test
    @Order(4)
    @DisplayName("Deve falhar ao procurar um item pelo ID")
    public void testFindItemById_NotFound() throws Exception {
        // Tenta buscar um item que não existe e espera um 404
        mockMvc.perform(get("/api/item/v1/find-by-id/{id}", 999L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @Order(5)
    @DisplayName("Deve deletar um item")
    public void testDeleteItem_Success() throws Exception {
        // Salva um item para ser excluído
        Item savedItem = itemRepository.save(
                new Item(
                        null,
                        "Brownie com sorvete",
                        "Sobremesa de chocolate",
                        25.00,
                        LocalConsumption.RESTAURANTE,
                        menu,
                        null));

        // Executa a requisição DELETE e espera um 204
        mockMvc.perform(delete("/api/item/v1/delete/{id}", savedItem.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        // Verifica se o item foi realmente excluído do banco
        assertEquals(Optional.empty(), itemRepository.findById(savedItem.getId()));
    }
}
