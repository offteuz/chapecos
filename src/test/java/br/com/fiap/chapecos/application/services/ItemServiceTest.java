package br.com.fiap.chapecos.application.services;

import br.com.fiap.chapecos.adapter.inbound.dto.request.ItemRequestDTO;
import br.com.fiap.chapecos.adapter.inbound.dto.response.ItemResponseDTO;
import br.com.fiap.chapecos.adapter.outbound.mapper.ItemMapper;
import br.com.fiap.chapecos.application.service.ItemService;
import br.com.fiap.chapecos.domain.model.*;
import br.com.fiap.chapecos.domain.repository.ItemRepository;
import br.com.fiap.chapecos.infrastructure.exception.ItemNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ItemServiceTest {

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private ItemMapper itemMapper;

    @InjectMocks
    private ItemService itemService;

    @Test
    void create_ShouldReturnItemResponse_WhenValidRequest() {
        // Arrange
        Address address = new Address(
                "12345-678",
                "Rua Exemplo",
                "123",
                "Bairro",
                "Cidade",
                "SP",
                "Brasil"
        );

        User user = new User();
        user.setId(1L);
        user.setUserName("user");
        user.setPassword("password");
        user.setEmail("user@restaurante.com");
        user.setAddress(address);

        Establishment establishment = new Establishment();
        establishment.setId(1L);
        establishment.setName("Restaurante Teste");
        establishment.setKitchenType(KitchenType.COMIDA_MINEIRA);
        establishment.setAddress(address);
        establishment.setUser(user);
        establishment.setRegistrationTimes(new HashSet<>());

        Menu menu = new Menu();
        menu.setId(1L);
        menu.setMenuType(MenuType.PRINCIPAL);
        menu.setEstablishment(establishment);

        Item item = new Item();
        item.setId(1L);
        item.setName("Arroz");
        item.setDescription("Comida");
        item.setPrice(10.50);
        item.setLocalConsumption(LocalConsumption.RESTAURANTE);
        item.setMenu(menu);

        ItemRequestDTO requestDTO = new ItemRequestDTO(
                "Arroz", "Comida", 10.50, LocalConsumption.RESTAURANTE, 1L
        );

        Mockito.when(itemMapper.toModel(requestDTO)).thenReturn(item);
        Mockito.when(itemRepository.save(item)).thenReturn(item);

        // Act
        ItemResponseDTO response = itemService.create(requestDTO);

        // Assert
        assertNotNull(response);
        assertEquals("Arroz", response.name());
        assertEquals("Comida", response.description());
        assertEquals(10.50, response.price());
        assertEquals(LocalConsumption.RESTAURANTE.getName(), response.localConsumption().name());
    }

    @Test
    void findById_ShouldReturnItemResponse_WhenItemExists() {
        // Arrange
        Address address = new Address(
                "12345-678",
                "Rua Exemplo",
                "123",
                "Bairro",
                "Cidade",
                "SP",
                "Brasil"
        );

        User user = new User();
        user.setId(1L);
        user.setUserName("user");
        user.setPassword("password");
        user.setEmail("user@restaurante.com");
        user.setAddress(address);

        Establishment establishment = new Establishment();
        establishment.setId(1L);
        establishment.setName("Restaurante Teste");
        establishment.setKitchenType(KitchenType.COMIDA_MINEIRA);
        establishment.setAddress(address);
        establishment.setUser(user);
        establishment.setRegistrationTimes(new HashSet<>());

        Menu menu = new Menu();
        menu.setId(1L);
        menu.setMenuType(MenuType.PRINCIPAL);
        menu.setEstablishment(establishment);

        Long id = 1L;

        Item item = new Item(
                id,
                "Arroz",
                "Comida",
                10.50,
                LocalConsumption.RESTAURANTE,
                menu,
                "https://www.pexels.com/pt-br/foto/33283959",
                new Audit()
        );

        Mockito.when(itemRepository.findById(id)).thenReturn(Optional.of(item));

        // Act
        ItemResponseDTO response = itemService.findById(id);

        // Assert
        assertNotNull(response);
        assertEquals("Arroz", response.name());
    }


    @Test
    void findById_ShouldThrowException_WhenItemDoesNotExist() {
        // Arrange
        Long id = 999L;

        Mockito.when(itemRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ItemNotFoundException.class, () -> itemService.findById(id));
    }

    @Test
    void findAll_ShouldReturnListOfItemResponseDTO() {
        // Arrange
        Address address = new Address(
                "12345-678",
                "Rua Exemplo",
                "123",
                "Bairro",
                "Cidade",
                "SP",
                "Brasil"
        );

        User user = new User();
        user.setId(1L);
        user.setUserName("user");
        user.setPassword("password");
        user.setEmail("user@restaurante.com");
        user.setAddress(address);

        Establishment establishment = new Establishment();
        establishment.setId(1L);
        establishment.setName("Restaurante Teste");
        establishment.setKitchenType(KitchenType.COMIDA_MINEIRA);
        establishment.setAddress(address);
        establishment.setUser(user);
        establishment.setRegistrationTimes(new HashSet<>());

        Menu menu = new Menu();
        menu.setId(1L);
        menu.setMenuType(MenuType.PRINCIPAL);
        menu.setEstablishment(establishment);

        List<Item> items = List.of(

                new Item(
                        1L,
                        "Arroz",
                        "Comida",
                        10.50,
                        LocalConsumption.RESTAURANTE,
                        menu,
                        "https://www.pexels.com/pt-br/foto/33283959",
                        new Audit()
                ),
                new Item(
                        1L,
                        "Feijão",
                        "Comida",
                        12.50,
                        LocalConsumption.RESTAURANTE,
                        menu,
                        "https://www.pexels.com/pt-br/foto/33283959",
                        new Audit()
                )
        );

        Mockito.when(itemRepository.findAll()).thenReturn(items);

        // Act
        List<ItemResponseDTO> responses = itemService.findAll();

        // Assert
        assertEquals(2, responses.size());
        assertEquals("Arroz", responses.get(0).name());
        assertEquals("Feijão", responses.get(1).name());
    }

    @Test
    void delete_ShouldCallRepositoryDelete_WhenItemExists() {
        // Arrange
        Address address = new Address(
                "12345-678",
                "Rua Exemplo",
                "123",
                "Bairro",
                "Cidade",
                "SP",
                "Brasil"
        );

        User user = new User();
        user.setId(1L);
        user.setUserName("user");
        user.setPassword("password");
        user.setEmail("user@restaurante.com");
        user.setAddress(address);

        Establishment establishment = new Establishment();
        establishment.setId(1L);
        establishment.setName("Restaurante Teste");
        establishment.setKitchenType(KitchenType.COMIDA_MINEIRA);
        establishment.setAddress(address);
        establishment.setUser(user);
        establishment.setRegistrationTimes(new HashSet<>());

        Menu menu = new Menu();
        menu.setId(1L);
        menu.setMenuType(MenuType.PRINCIPAL);
        menu.setEstablishment(establishment);

        Long id = 1L;
        Item item = new Item(
                1L,
                "Arroz",
                "Comida",
                10.50,
                LocalConsumption.RESTAURANTE,
                menu,
                "https://www.pexels.com/pt-br/foto/33283959",
                new Audit()
        );

        Mockito.when(itemRepository.findById(id)).thenReturn(Optional.of(item));

        // Act
        itemService.delete(id);

        // Assert
        Mockito.verify(itemRepository).delete(item);
    }

    @Test
    void delete_ShouldThrowException_WhenItemDoesNotExist() {
        // Arrange
        Long id = 999L;

        Mockito.when(itemRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ItemNotFoundException.class, () -> itemService.delete(id));
    }

}

