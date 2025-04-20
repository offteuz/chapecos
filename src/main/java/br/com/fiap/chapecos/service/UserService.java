package br.com.fiap.chapecos.service;

import br.com.fiap.chapecos.dto.request.UserRequestDTO;
import br.com.fiap.chapecos.dto.request.UserUpdatePasswordRequestDTO;
import br.com.fiap.chapecos.dto.response.UserResponseDTO;
import br.com.fiap.chapecos.exception.EmailAlreadyExistsException;
import br.com.fiap.chapecos.exception.UserAlreadyExistsException;
import br.com.fiap.chapecos.exception.UserNotFoundException;
import br.com.fiap.chapecos.mapper.UserMapper;
import br.com.fiap.chapecos.model.Role;
import br.com.fiap.chapecos.model.User;
import br.com.fiap.chapecos.repository.RoleRepository;
import br.com.fiap.chapecos.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    public final UserRepository userRepository;

    public final RoleRepository roleRepository;

    public final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.roleRepository = roleRepository;
    }

    public UserResponseDTO create(UserRequestDTO dto) {
        User user = userMapper.user(dto);

        if (userRepository.existsUserByEmail(dto.email())) {
            throw new EmailAlreadyExistsException("O e-mail informado já está em uso por outro usuário. Verifique!");
        }

        if (userRepository.existsUserByUserName(dto.userName())) {
            throw new UserAlreadyExistsException("O nome de usuário informado já está em uso por outro usuário. Verifique!");
        }

        Role role = roleRepository.findById(dto.roleId())
                .orElseThrow(() -> new RuntimeException("Papel de usuário não entrada. Verifique!"));

        user.setRole(role);

        return new UserResponseDTO(userRepository.save(user));
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

    public UserResponseDTO update(Long id, UserRequestDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        userMapper.user(dto);

        return new UserResponseDTO(userRepository.save(user));
    }

    public void delete(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        userRepository.delete(user);
    }

    public void updatePassword(Long id, UserUpdatePasswordRequestDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado. Verifique!"));

        if (!dto.currentPassword().equals(user.getPassword())) {
            throw new IllegalArgumentException("Senha atual inválida!");
        }

        user.setPassword(dto.newPassword());
        userRepository.save(user);
    }
}
