package br.com.fiap.chapecos.application.services;

import br.com.fiap.chapecos.adapter.inbound.dto.request.KitchenTypeRequestDTO;
import br.com.fiap.chapecos.adapter.inbound.dto.request.RegistrationTimeRequestDTO;
import br.com.fiap.chapecos.adapter.inbound.dto.response.KitchenTypeResponseDTO;
import br.com.fiap.chapecos.adapter.inbound.dto.response.RegistrationTimeResponseDTO;
import br.com.fiap.chapecos.adapter.outbound.mapper.RegistrationTimeMapper;
import br.com.fiap.chapecos.application.service.RegistrationTimeService;
import br.com.fiap.chapecos.domain.model.*;
import br.com.fiap.chapecos.domain.repository.EstablishmentRepository;
import br.com.fiap.chapecos.domain.repository.RegistrationTimeRepository;
import br.com.fiap.chapecos.infrastructure.exception.EstablishmentNotFoundException;
import br.com.fiap.chapecos.infrastructure.exception.RegistrationTimeNotFoundException;
import br.com.fiap.chapecos.logic.ValidatorSchedules;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RegistrationTimeServiceTest {

    private RegistrationTimeRepository registrationTimeRepository;
    private EstablishmentRepository establishmentRepository;
    private RegistrationTimeMapper registrationTimeMapper;
    private ValidatorSchedules validatorSchedules;
    private RegistrationTimeService registrationTimeService;

    @BeforeEach
    void setUp() {
        registrationTimeRepository = mock(RegistrationTimeRepository.class);
        establishmentRepository = mock(EstablishmentRepository.class);
        registrationTimeMapper = mock(RegistrationTimeMapper.class);
        validatorSchedules = mock(ValidatorSchedules.class);
        registrationTimeService = new RegistrationTimeService(
                registrationTimeRepository,
                registrationTimeMapper,
                validatorSchedules,
                establishmentRepository
        );
    }

    @Test
    void create_ShouldReturnResponse_WhenValidRequest() {
        // Arrange
        DayOfWeek dayOfWeek = DayOfWeek.MONDAY;
        LocalTime openingTime = LocalTime.of(8, 0);
        LocalTime closingTime = LocalTime.of(17, 0);

        // Mock do DTO de entrada
        RegistrationTimeRequestDTO request = new RegistrationTimeRequestDTO(
                dayOfWeek,
                openingTime,
                closingTime,
                1L // ID fictício do estabelecimento
        );

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

        // Mock do estabelecimento
        Establishment establishment = new Establishment();
        establishment.setId(1L);
        establishment.setName("Teste Restaurante");
        establishment.setKitchenType(KitchenType.COMIDA_MINEIRA);
        establishment.setRegistrationTimes(new HashSet<>());
        establishment.setAddress(address);
        establishment.setUser(user);

        // Mock do registrationTime
        RegistrationTime registrationTime = new RegistrationTime();
        registrationTime.setDayOfWeek(dayOfWeek);
        registrationTime.setOpening(openingTime);
        registrationTime.setClosing(closingTime);
        registrationTime.setEstablishment(establishment);

        // Mock do repository
        when(establishmentRepository.findById(1L)).thenReturn(Optional.of(establishment));
        when(registrationTimeMapper.toModel(request)).thenReturn(registrationTime);
        when(registrationTimeRepository.save(registrationTime)).thenReturn(registrationTime);

        // Act
        RegistrationTimeResponseDTO responseDTO = registrationTimeService.create(request);

        // Assert
        assertNotNull(responseDTO);
        assertEquals(dayOfWeek, responseDTO.dayOfWeek());
        assertEquals(openingTime, responseDTO.opening());
        assertEquals(closingTime, responseDTO.closing());
        assertNotNull(responseDTO.establishment());
        assertEquals("Teste Restaurante", responseDTO.establishment().name());
    }

    @Test
    void create_ShouldThrowException_WhenEstablishmentNotFound() {
        RegistrationTimeRequestDTO request = new RegistrationTimeRequestDTO(
                DayOfWeek.MONDAY,
                LocalTime.of(9, 0),
                LocalTime.of(17, 0),
                1L
        );
        when(establishmentRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(EstablishmentNotFoundException.class, () -> registrationTimeService.create(request));
    }

    @Test
    void findAll_ShouldReturnListOfDTO() {

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

        // Mock do estabelecimento
        Establishment establishment = new Establishment();
        establishment.setId(1L);
        establishment.setName("Teste Restaurante");
        establishment.setKitchenType(KitchenType.COMIDA_MINEIRA);
        establishment.setRegistrationTimes(new HashSet<>());
        establishment.setAddress(address);
        establishment.setUser(user);

        RegistrationTime time1 = new RegistrationTime();
        time1.setDayOfWeek(DayOfWeek.MONDAY);
        time1.setOpening(LocalTime.of(8, 0));
        time1.setClosing(LocalTime.of(17, 0));
        time1.setEstablishment(establishment);

        RegistrationTime time2 = new RegistrationTime();
        time2.setDayOfWeek(DayOfWeek.TUESDAY);
        time2.setOpening(LocalTime.of(10, 0));
        time2.setClosing(LocalTime.of(18, 0));
        time2.setEstablishment(establishment);

        when(registrationTimeRepository.findAll()).thenReturn(List.of(time1, time2));

        List<RegistrationTimeResponseDTO> result = registrationTimeService.findAll();

        assertEquals(2, result.size());
        assertEquals(DayOfWeek.MONDAY, result.get(0).dayOfWeek());
        assertEquals(DayOfWeek.TUESDAY, result.get(1).dayOfWeek());
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

        // Mock do estabelecimento
        Establishment establishment = new Establishment();
        establishment.setId(1L);
        establishment.setName("Teste Restaurante");
        establishment.setKitchenType(KitchenType.COMIDA_MINEIRA);
        establishment.setRegistrationTimes(new HashSet<>());
        establishment.setAddress(address);
        establishment.setUser(user);

        RegistrationTime time = new RegistrationTime();
        time.setDayOfWeek(DayOfWeek.FRIDAY);
        time.setOpening(LocalTime.of(9, 0));
        time.setClosing(LocalTime.of(15, 0));
        time.setEstablishment(establishment);

        when(registrationTimeRepository.findById(1L)).thenReturn(Optional.of(time));

        RegistrationTimeResponseDTO result = registrationTimeService.findById(1L);

        assertEquals(DayOfWeek.FRIDAY, result.dayOfWeek());
    }

    @Test
    void findById_ShouldThrowException_WhenNotFound() {
        when(registrationTimeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RegistrationTimeNotFoundException.class, () -> registrationTimeService.findById(1L));
    }

    @Test
    void delete_ShouldCallRepositoryDelete_WhenFound() {
        RegistrationTime time = new RegistrationTime();
        when(registrationTimeRepository.findById(1L)).thenReturn(Optional.of(time));

        registrationTimeService.delete(1L);

        verify(registrationTimeRepository).delete(time);
    }

    @Test
    void delete_ShouldThrowException_WhenNotFound() {
        when(registrationTimeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RegistrationTimeNotFoundException.class, () -> registrationTimeService.delete(1L));
    }

}
