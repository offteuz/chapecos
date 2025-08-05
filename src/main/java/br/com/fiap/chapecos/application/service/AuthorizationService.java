package br.com.fiap.chapecos.application.service;

import br.com.fiap.chapecos.infrastructure.exception.UserNotFoundException;
import br.com.fiap.chapecos.domain.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {

    private final UserRepository userRepository;

    public AuthorizationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String input) throws UsernameNotFoundException {
        return userRepository.findByUserName(input)
                .or(() -> userRepository.findByEmail(input))
                .orElseThrow(UserNotFoundException::new);
    }
}
