package br.com.fiap.chapecos.application.services;

import br.com.fiap.chapecos.application.service.AuthorizationService;
import br.com.fiap.chapecos.domain.model.User;
import br.com.fiap.chapecos.domain.repository.UserRepository;
import br.com.fiap.chapecos.infrastructure.exception.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class AuthorizationServiceTest {

    private AuthorizationService authorizationService;
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        authorizationService = new AuthorizationService(userRepository);
    }

    @Test
    @DisplayName("Deve retornar UserDetails ao buscar por username")
    void loadUserByUsername_ShouldReturnUser_WhenUsernameExists() {
        // Arrange
        String username = "usuario123";
        User user = new User();
        user.setUserName(username);
        when(userRepository.findByUserName(username)).thenReturn(Optional.of(user));

        // Act
        UserDetails result = authorizationService.loadUserByUsername(username);

        // Assert
        assertEquals(username, result.getUsername());
        verify(userRepository, times(1)).findByUserName(username);
        verify(userRepository, never()).findByEmail(any());
    }

    @Test
    @DisplayName("Deve retornar UserDetails ao buscar por email quando username não existir")
    void loadUserByUsername_ShouldReturnUser_WhenEmailExists() {
        // Arrange
        String input = "email@teste.com";

        User user = new User();
        user.setEmail(input);

        when(userRepository.findByUserName(input)).thenReturn(Optional.empty());
        when(userRepository.findByEmail(input)).thenReturn(Optional.of(user));

        // Act
        UserDetails result = authorizationService.loadUserByUsername(input);

        // Assert
        assertEquals(input, ((User) result).getEmail()); // Ou getEmail() dependendo de como você implementa o UserDetails
        verify(userRepository, times(1)).findByUserName(input);
        verify(userRepository, times(1)).findByEmail(input);
    }

    @Test
    @DisplayName("Deve lançar exceção se usuário não for encontrado por username ou email")
    void loadUserByUsername_ShouldThrowException_WhenUserNotFound() {
        // Arrange
        String input = "naoexiste";
        when(userRepository.findByUserName(input)).thenReturn(Optional.empty());
        when(userRepository.findByEmail(input)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UserNotFoundException.class, () -> authorizationService.loadUserByUsername(input));
        verify(userRepository, times(1)).findByUserName(input);
        verify(userRepository, times(1)).findByEmail(input);
    }
}
