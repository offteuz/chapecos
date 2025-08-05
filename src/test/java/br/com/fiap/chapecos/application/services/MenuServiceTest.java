package br.com.fiap.chapecos.application.services;

import br.com.fiap.chapecos.adapter.inbound.dto.request.MenuRequestDTO;
import br.com.fiap.chapecos.adapter.inbound.dto.response.MenuResponseDTO;
import br.com.fiap.chapecos.adapter.outbound.mapper.MenuMapper;
import br.com.fiap.chapecos.application.service.MenuService;
import br.com.fiap.chapecos.domain.model.*;
import br.com.fiap.chapecos.domain.repository.MenuRepository;
import br.com.fiap.chapecos.infrastructure.exception.MenuNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MenuServiceTest {

    @Mock
    private MenuRepository menuRepository;

    @Mock
    private MenuMapper menuMapper;

    @InjectMocks
    private MenuService menuService;

    @Test
    void create_ShouldReturnResponseDTO_WhenValidRequest() {
        // Arrange
        MenuRequestDTO dto = new MenuRequestDTO(MenuType.PRINCIPAL, 1L);

        // Mock do Usuário
        User user = new User();
        user.setId(1L);
        user.setUserName("user");
        user.setPassword("password");
        user.setEmail("user@restaurante.com");
        user.setAddress(new Address(
                "11111-111",
                "Rua Usuário",
                "321",
                "Vizinhança",
                "São Paulo",
                "São Paulo",
                "Brasil"));

        // Mock do endereço
        Address address = new Address();
        address.setPostalCode("12345-678");
        address.setStreet("Rua Exemplo");
        address.setNumber("123");
        address.setCity("Cidade Teste");
        address.setState("SP");
        address.setCountry("Brasil");
        address.setNeighborhood("Rua Exemplo");

        Establishment establishment = new Establishment();
        establishment.setId(1L);
        establishment.setName("Teste Restaurante");
        establishment.setKitchenType(KitchenType.COMIDA_MINEIRA);
        establishment.setRegistrationTimes(new HashSet<>());
        establishment.setAddress(address);
        establishment.setUser(user);

        Menu menu = new Menu();
        menu.setId(1L);
        menu.setMenuType(MenuType.PRINCIPAL);
        menu.setEstablishment(establishment);

        when(menuMapper.toModel(dto)).thenReturn(menu);
        when(menuRepository.save(menu)).thenReturn(menu);

        // Act
        MenuResponseDTO response = menuService.create(dto);

        // Assert
        assertNotNull(response);
        assertEquals(1L, response.establishment().idEstablishment());
        assertEquals(MenuType.PRINCIPAL.getName(), response.menuType().name());
    }

    @Test
    void findAll_ShouldReturnListOfDTOs() {
        // Mock do Usuário
        User user = new User();
        user.setId(1L);
        user.setUserName("user");
        user.setPassword("password");
        user.setEmail("user@restaurante.com");
        user.setAddress(new Address(
                "11111-111",
                "Rua Usuário",
                "321",
                "Vizinhança",
                "São Paulo",
                "São Paulo",
                "Brasil"));

        // Mock do Endereço
        Address address = new Address();
        address.setPostalCode("12345-678");
        address.setStreet("Rua Exemplo");
        address.setNumber("123");
        address.setCity("Cidade Teste");
        address.setState("SP");
        address.setCountry("Brasil");
        address.setNeighborhood("Rua Exemplo");

        Establishment establishment = new Establishment();
        establishment.setId(1L);
        establishment.setName("Teste Restaurante");
        establishment.setKitchenType(KitchenType.COMIDA_MINEIRA);
        establishment.setRegistrationTimes(new HashSet<>());
        establishment.setAddress(address);
        establishment.setUser(user);

        // Arrange
        Menu menu1 = new Menu();
        menu1.setId(1L);
        menu1.setMenuType(MenuType.ENTRADA);
        menu1.setEstablishment(establishment);
        menu1.setAudit(new Audit());

        Menu menu2 = new Menu();
        menu2.setId(1L);
        menu2.setMenuType(MenuType.PRINCIPAL);
        menu2.setEstablishment(establishment);
        menu2.setAudit(new Audit());

        when(menuRepository.findAll()).thenReturn(List.of(menu1, menu2));

        // Act
        List<MenuResponseDTO> result = menuService.findAll();

        // Assert
        assertEquals(2, result.size());
        assertEquals(MenuType.ENTRADA.getName(), result.get(0).menuType().name());
        assertEquals(MenuType.PRINCIPAL.getName(), result.get(1).menuType().name());
    }

    @Test
    void findById_ShouldReturnDTO_WhenExists() {
        // Mock do Usuário
        User user = new User();
        user.setId(1L);
        user.setUserName("user");
        user.setPassword("password");
        user.setEmail("user@restaurante.com");
        user.setAddress(new Address(
                "11111-111",
                "Rua Usuário",
                "321",
                "Vizinhança",
                "São Paulo",
                "São Paulo",
                "Brasil"));

        // Mock do endereço
        Address address = new Address();
        address.setPostalCode("12345-678");
        address.setStreet("Rua Exemplo");
        address.setNumber("123");
        address.setCity("Cidade Teste");
        address.setState("SP");
        address.setCountry("Brasil");
        address.setNeighborhood("Rua Exemplo");

        Establishment establishment = new Establishment();
        establishment.setId(1L);
        establishment.setName("Teste Restaurante");
        establishment.setKitchenType(KitchenType.COMIDA_MINEIRA);
        establishment.setRegistrationTimes(new HashSet<>());
        establishment.setAddress(address);
        establishment.setUser(user);

        // Arrange
        Menu menu = new Menu();
        menu.setId(1L);
        menu.setMenuType(MenuType.SOBREMESA);
        menu.setEstablishment(establishment);
        menu.setAudit(new Audit());

        when(menuRepository.findById(1L)).thenReturn(Optional.of(menu));

        // Act
        MenuResponseDTO response = menuService.findById(1L);

        // Assert
        assertNotNull(response);
        assertEquals(1L, response.idMenu());
        assertEquals(MenuType.SOBREMESA.getName(), response.menuType().name());
    }

    @Test
    void findById_ShouldThrowException_WhenNotFound() {
        // Arrange
        when(menuRepository.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(MenuNotFoundException.class, () -> menuService.findById(99L));
    }

    @Test
    void delete_ShouldCallRepository_WhenMenuExists() {
        // Arrange
        Menu menu = new Menu(1L, MenuType.SOBREMESA, new HashSet<>(), null, new Audit());

        when(menuRepository.findById(1L)).thenReturn(Optional.of(menu));

        // Act
        menuService.delete(1L);

        // Assert
        verify(menuRepository).delete(menu);
    }

    @Test
    void delete_ShouldThrowException_WhenMenuNotFound() {
        // Arrange
        when(menuRepository.findById(42L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(MenuNotFoundException.class, () -> menuService.delete(42L));
    }
}
