package br.com.fiap.chapecos.service;

import br.com.fiap.chapecos.dto.request.UserUpdatePasswordRequestDTO;
import br.com.fiap.chapecos.dto.request.UserUpdateRequestDTO;
import br.com.fiap.chapecos.dto.request.UserUpdateRoleRequestDTO;
import br.com.fiap.chapecos.dto.response.UserResponseDTO;
import br.com.fiap.chapecos.dto.response.UserUpdateResponseDTO;
import br.com.fiap.chapecos.exception.PasswordInvalidException;
import br.com.fiap.chapecos.exception.UserNotFoundException;
import br.com.fiap.chapecos.mapper.AddressMapper;
import br.com.fiap.chapecos.mapper.UserMapper;
import br.com.fiap.chapecos.model.Address;
import br.com.fiap.chapecos.model.User;
import br.com.fiap.chapecos.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    public final UserRepository userRepository;

    public final UserMapper userMapper;

    public final AddressMapper addressMapper;

    public final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserMapper userMapper, AddressMapper addressMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.addressMapper = addressMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserResponseDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserResponseDTO::new)
                .toList();
    }

    public UserResponseDTO findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        return new UserResponseDTO(user);
    }

    public UserUpdateResponseDTO update(Long id, UserUpdateRequestDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        userMapper.updateAll(dto, user);

        return new UserUpdateResponseDTO(userRepository.save(user));
    }

    public void delete(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        userRepository.delete(user);
    }

    public void updatePassword(Long id, UserUpdatePasswordRequestDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado. Verifique!"));

        if (!passwordEncoder.matches(dto.currentPassword(), user.getPassword())) {
            throw new PasswordInvalidException("Senha atual inválida!");
        }

        user.setPassword(passwordEncoder.encode(dto.newPassword()));
        userRepository.save(user);
    }

    public void updateRole(Long id, UserUpdateRoleRequestDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado. Verifique!"));

        user.setRole(dto.role());
        userRepository.save(user);
    }
}
