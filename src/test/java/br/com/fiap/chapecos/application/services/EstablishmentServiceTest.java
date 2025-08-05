package br.com.fiap.chapecos.application.services;

import br.com.fiap.chapecos.adapter.inbound.dto.request.AddressRequestDTO;
import br.com.fiap.chapecos.adapter.inbound.dto.request.EstablishmentRequestDTO;
import br.com.fiap.chapecos.adapter.inbound.dto.response.AddressResponseDTO;
import br.com.fiap.chapecos.adapter.inbound.dto.response.EstablishmentResponseDTO;
import br.com.fiap.chapecos.adapter.inbound.dto.response.KitchenTypeResponseDTO;
import br.com.fiap.chapecos.adapter.inbound.dto.response.UserResponseDTO;
import br.com.fiap.chapecos.adapter.outbound.mapper.AddressMapper;
import br.com.fiap.chapecos.adapter.outbound.mapper.EstablishmentMapper;
import br.com.fiap.chapecos.application.service.EstablishmentService;
import br.com.fiap.chapecos.domain.model.*;
import br.com.fiap.chapecos.domain.repository.EstablishmentRepository;
import br.com.fiap.chapecos.infrastructure.exception.EstablishmentNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EstablishmentServiceTest {

    @InjectMocks
    private EstablishmentService establishmentService;

    @Mock
    private EstablishmentRepository establishmentRepository;

    @Mock
    private EstablishmentMapper establishmentMapper;

    @Mock
    private AddressMapper addressMapper;

    @Test
    void findById_ShouldReturnEstablishment_WhenItExists() {
        // Arrange
        Establishment establishment = new Establishment();
        establishment.setId(1L);
        establishment.setName("Restaurante Teste");
        establishment.setKitchenType(KitchenType.COMIDA_MINEIRA);
        establishment.setAddress(new Address());
        establishment.setUser(new User());
        establishment.setRegistrationTimes(new HashSet<>());

        Long id = 1L;

        Mockito.when(establishmentRepository.findById(id))
                .thenReturn(Optional.of(establishment));

        EstablishmentResponseDTO response = establishmentService.findById(id);

        assertNotNull(response);
        assertEquals("Restaurante Teste", response.name());
    }

    @Test
    void findById_ShouldThrowException_WhenNotFound() {
        Long id = 1L;

        Mockito.when(establishmentRepository.findById(id))
                .thenReturn(Optional.empty());

        assertThrows(EstablishmentNotFoundException.class, () -> establishmentService.findById(id));
    }

    @Test
    void create_ShouldSaveEstablishment() {
        // Arrange
        AddressRequestDTO addressRequest = new AddressRequestDTO(
                "12345-678",
                "Rua Exemplo",
                "123",
                "Bairro",
                "Cidade",
                "SP",
                "Brasil"
        );

        EstablishmentRequestDTO request = new EstablishmentRequestDTO(
                "Restaurante A",
                "12.345.6789-01",
                KitchenType.COMIDA_MINEIRA,
                addressRequest,
                "00:00:00",
                1L
        );

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
        user.setPassword("pass");
        user.setEmail("user@teste.com");
        user.setAddress(address);
        user.setRole(Role.ADMIN);

        Establishment establishment = new Establishment();
        establishment.setId(1L);
        establishment.setName("Restaurante A");
        establishment.setCnpj("12.345.6789-01");
        establishment.setKitchenType(KitchenType.COMIDA_MINEIRA);
        establishment.setAddress(address);
        establishment.setUser(user);
        establishment.setRegistrationTimes(new HashSet<>());
        establishment.setAudit(new Audit());

        EstablishmentResponseDTO expectedResponse = new EstablishmentResponseDTO(
                1L,
                "Restaurante A",
                "12.345.6789-01",
                new KitchenTypeResponseDTO(KitchenType.COMIDA_MINEIRA),
                new AddressResponseDTO(
                        "12345-678",
                        "Rua Exemplo",
                        "123",
                        "Bairro",
                        "Cidade",
                        "SP",
                        "Brasil"
                ),
                "00:00:00",
                new UserResponseDTO(user),
                new HashSet<>(),
                establishment.getAudit()
        );

        Mockito.when(establishmentMapper.toModel(request)).thenReturn(establishment);
        Mockito.when(establishmentRepository.save(Mockito.any())).thenReturn(establishment);

        // Act
        EstablishmentResponseDTO response = establishmentService.create(request);

        // Assert
        assertNotNull(response);
        assertEquals("Restaurante A", response.name());
        assertEquals(KitchenType.COMIDA_MINEIRA.getName(), response.kitchenType().name());
    }

    @Test
    void delete_ShouldRemoveEstablishment_WhenExists() {
        // Arrange
        Long id = 1L;
        Establishment establishment = new Establishment();
        Mockito.when(establishmentRepository.findById(id)).thenReturn(Optional.of(establishment));

        // Act
        establishmentService.delete(id);

        // Assert
        Mockito.verify(establishmentRepository).delete(establishment);
    }

    @Test
    void delete_ShouldThrowException_WhenNotFound() {
        // Arrange
        Long id = 1L;
        Mockito.when(establishmentRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(EstablishmentNotFoundException.class, () -> establishmentService.delete(id));

        // Extra: certifique-se de que delete() não foi chamado
        Mockito.verify(establishmentRepository, Mockito.never()).delete(Mockito.any());
    }
}