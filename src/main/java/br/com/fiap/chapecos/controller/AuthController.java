package br.com.fiap.chapecos.controller;

import br.com.fiap.chapecos.dto.request.UserLoginRequestDTO;
import br.com.fiap.chapecos.dto.request.UserRequestDTO;
import br.com.fiap.chapecos.dto.response.TokenResponseDTO;
import br.com.fiap.chapecos.model.User;
import br.com.fiap.chapecos.repository.UserRepository;
import br.com.fiap.chapecos.service.TokenService;
import br.com.fiap.chapecos.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    private final UserRepository userRepository;

    private final TokenService tokenService;

    public AuthController(AuthenticationManager authenticationManager, UserService userService, UserRepository userRepository, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    @PostMapping("/auth/login/v0")
    public ResponseEntity login(@Valid @RequestBody UserLoginRequestDTO dto) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(dto.identifier(), dto.password());

        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        String token = tokenService.tokengenerate((User) authentication.getPrincipal());

        return ResponseEntity.ok(new TokenResponseDTO(token));
    }

    @PostMapping("/auth/register/v0")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity register(@Valid @RequestBody UserRequestDTO dto) {
        if (this.userRepository.existsUserByUserName(dto.userName())) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.password());
        User newUser = new User(dto.email(), dto.userName(), encryptedPassword, dto.address(), dto.role());

        this.userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }
}
