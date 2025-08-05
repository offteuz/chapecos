package br.com.fiap.chapecos.application.services;

import br.com.fiap.chapecos.adapter.inbound.dto.request.AddressRequestDTO;
import br.com.fiap.chapecos.adapter.inbound.dto.request.UserUpdatePasswordRequestDTO;
import br.com.fiap.chapecos.adapter.inbound.dto.request.UserUpdateRequestDTO;
import br.com.fiap.chapecos.adapter.inbound.dto.request.UserUpdateRoleRequestDTO;
import br.com.fiap.chapecos.adapter.inbound.dto.response.UserResponseDTO;
import br.com.fiap.chapecos.adapter.outbound.mapper.AddressMapper;
import br.com.fiap.chapecos.adapter.outbound.mapper.UserMapper;
import br.com.fiap.chapecos.application.service.UserService;
import br.com.fiap.chapecos.domain.model.Address;
import br.com.fiap.chapecos.domain.model.Role;
import br.com.fiap.chapecos.domain.model.User;
import br.com.fiap.chapecos.infrastructure.exception.PasswordInvalidException;
import br.com.fiap.chapecos.infrastructure.exception.UserNotFoundException;
import br.com.fiap.chapecos.domain.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private AddressMapper addressMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test // -> Funcional
    void testFindAll_returnsListOfUserResponseDTO() {
        // Criação de usuários mockados
        User user1 = new User("email1@test.com", "username1", "encoded-password", new Address());
        user1.setId(1L);

        User user2 = new User("email2@test.com", "username2", "encoded-password", new Address());
        user2.setId(2L);

        // Configuração do comportamento do mock
        when(userRepository.findAll()).thenReturn(List.of(user1, user2));

        // Chamada ao método do serviço
        List<UserResponseDTO> response = userService.findAll();

        // Verificações (asserts)
        assertEquals(2, response.size());
        assertEquals("email1@test.com", response.get(0).email());
        assertEquals("email2@test.com", response.get(1).email());
    }

    @Test
    void testFindById_userExists_returnsUserResponseDTO() {
        User user = new User("email@test.com", "username", "encoded-password", new Address());
        user.setId(1L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        UserResponseDTO responseDTO = userService.findById(1L);

        assertEquals("email@test.com", responseDTO.email());
    }

    @Test
    void testFindById_userNotFound_throwsException() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.findById(99L));
    }

    @Test
    void testUpdate_userExists_updatesAndReturnsResponseDTO() {
        Long userId = 1L;
        User user = new User();
        user.setEmail("email@test.com");
        user.setUserName("username");
        user.setPassword("encoded-password");
        user.setAddress(new Address());
        user.setId(1L);

        user.setId(userId);
        AddressRequestDTO addressDto = new AddressRequestDTO(
                "Rua Exemplo",
                "123",
                "Bairro",
                "Cidade",
                "UF",
                "12345-678",
        "Brazil"
        );

        UserUpdateRequestDTO dto = new UserUpdateRequestDTO(
                "NovoNome",
                "novoemail@email.com",
                addressDto
        );

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserResponseDTO response = userService.update(userId, dto);

        verify(userRepository).save(any(User.class));

        assertEquals(userId, response.id());
    }

    @Test
    void testDelete_userExists_deletesUser() {
        User user = new User();
        user.setEmail("email@test.com");
        user.setUserName("username");
        user.setPassword("encoded-password");
        user.setAddress(new Address());
        user.setId(1L);

        user.setId(1L);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        userService.delete(1L);

        verify(userRepository, times(1)).delete(user);
    }

    @Test
    void testDelete_userNotFound_throwsException() {
        when(userRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.delete(999L));
    }

    @Test
    void testUpdatePassword_invalidCurrentPassword_throwsException() {
        User user = new User();
        user.setEmail("email@test.com");
        user.setUserName("username");
        user.setPassword("encoded-password");
        user.setAddress(new Address());
        user.setId(1L);

        user.setId(1L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("wrong-password", "encoded-password")).thenReturn(false);

        UserUpdatePasswordRequestDTO dto = new UserUpdatePasswordRequestDTO("wrong-password", "new-password");

        assertThrows(PasswordInvalidException.class, () -> userService.updatePassword(1L, dto));
    }

    @Test
    void testUpdatePassword_validCurrentPassword_updatesPassword() {
        User user = new User();
        user.setEmail("email@test.com");
        user.setUserName("username");
        user.setPassword("encoded-password");
        user.setAddress(new Address());
        user.setId(1L);

        user.setId(1L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("encoded-password", "encoded-password")).thenReturn(true);
        when(passwordEncoder.encode("new-password")).thenReturn("new-encoded");

        UserUpdatePasswordRequestDTO dto = new UserUpdatePasswordRequestDTO("encoded-password", "new-password");

        userService.updatePassword(1L, dto);

        verify(userRepository, times(1)).save(user);
        assertEquals("new-encoded", user.getPassword());
    }

    @Test
    void testUpdateRole_userExists_updatesRole() {
        User user = new User();
        user.setEmail("email@test.com");
        user.setUserName("username");
        user.setPassword("encoded-password");
        user.setAddress(new Address());
        user.setId(1L);

        user.setId(1L);

        UserUpdateRoleRequestDTO dto = new UserUpdateRoleRequestDTO(Role.ADMIN);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        userService.updateRole(1L, dto);

        verify(userRepository, times(1)).save(user);

        assertEquals(Role.ADMIN, user.getRole());
    }

    @Test
    void testUpdateRole_userNotFound_throwsException() {
        when(userRepository.findById(999L)).thenReturn(Optional.empty());

        UserUpdateRoleRequestDTO dto = new UserUpdateRoleRequestDTO(Role.ADMIN);

        assertThrows(UserNotFoundException.class, () -> userService.updateRole(999L, dto));
    }
}