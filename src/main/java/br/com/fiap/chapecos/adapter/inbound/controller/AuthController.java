package br.com.fiap.chapecos.controller;

import br.com.fiap.chapecos.dto.request.UserLoginRequestDTO;
import br.com.fiap.chapecos.dto.request.UserRequestDTO;
import br.com.fiap.chapecos.dto.response.TokenResponseDTO;
import br.com.fiap.chapecos.mapper.AddressMapper;
import br.com.fiap.chapecos.domain.model.Address;
import br.com.fiap.chapecos.domain.model.User;
import br.com.fiap.chapecos.domain.repository.UserRepository;
import br.com.fiap.chapecos.application.service.TokenService;
import io.swagger.v3.oas.annotations.Operation;
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

    private final UserRepository userRepository;

    private final TokenService tokenService;

    private final AddressMapper addressMapper;

    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository, TokenService tokenService, AddressMapper addressMapper) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.addressMapper = addressMapper;
    }

    @Operation(
            summary = "Realiza o `login` do usuário.",
            description = "Realiza o `login` do usuário desde que as informações passadas na autenticação existam na base de dados, um token de acesso será gerado ao usuário, independente da sua função. Não exige privilégios."
    )
    @PostMapping("/auth/v1/login")
    public ResponseEntity login(@Valid @RequestBody UserLoginRequestDTO dto) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(dto.identifier(), dto.password());

        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        String token = tokenService.tokengenerate((User) authentication.getPrincipal());

        return ResponseEntity.ok(new TokenResponseDTO(token));
    }

    @Operation(
            summary = "Realiza o cadastro do usuário.",
            description = "Realiza o cadastro do usuário na base de dados, sendo obrigatório para a geração do token de acesso. Não exige privilégios."
    )
    @PostMapping("/auth/v1/register")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity register(@Valid @RequestBody UserRequestDTO dto) {
        if (this.userRepository.existsUserByUserName(dto.userName()) || this.userRepository.existsUserByEmail(dto.email())) {
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.password());

        Address address = addressMapper.toDto(dto.address());

        User newUser = new User(dto.email(), dto.userName(), encryptedPassword, address);

        this.userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }
}
