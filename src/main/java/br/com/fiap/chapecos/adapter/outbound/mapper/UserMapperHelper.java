package br.com.fiap.chapecos.adapter.outbound.mapper;

import br.com.fiap.chapecos.infrastructure.exception.UserNotFoundException;
import br.com.fiap.chapecos.domain.model.User;
import br.com.fiap.chapecos.domain.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UserMapperHelper {

    private final UserRepository userRepository;

    public UserMapperHelper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User map(Long idUser) {
        return userRepository.findById(idUser)
                .orElseThrow(UserNotFoundException::new);
    }
}
