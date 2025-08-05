package br.com.fiap.chapecos.adapter.inbound.controller;

import br.com.fiap.chapecos.adapter.inbound.dto.request.UserUpdatePasswordRequestDTO;
import br.com.fiap.chapecos.adapter.inbound.dto.request.UserUpdateRequestDTO;
import br.com.fiap.chapecos.adapter.inbound.dto.request.UserUpdateRoleRequestDTO;
import br.com.fiap.chapecos.adapter.inbound.dto.response.UserResponseDTO;
import br.com.fiap.chapecos.application.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    public final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(
            summary = "Busca todos os usuários.",
            description = "Busca todos os usuários na base de dados, sem restrição. Exige privilégios de ADMINISTRADOR."
    )
    @GetMapping("/user/v1/find-all")
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponseDTO> findAll() {
        return userService.findAll();
    }

    @Operation(
            summary = "Busca um usuário pelo seu ID.",
            description = "Busca um único usuário por vez na base de dados, desde que o seu ID exista. Exige privilégios de ADMINISTRADOR."
    )
    @GetMapping("/user/v1/find-by-id/{idUser}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDTO findById(@PathVariable Long idUser) {
        return userService.findById(idUser);
    }

    @Operation(
            summary = "Atualiza um usuário pelo seu ID.",
            description = "Atualiza um único usuário por vez na base de dados, desde que o seu ID exista, sendo as possibilidades de alteração: E-mail, nome de usuário e endereço. Exige privilégios de ADMINISTRADOR."
    )
    @PutMapping("/user/v1/update/{idUser}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDTO update(@PathVariable Long idUser, @Valid @RequestBody UserUpdateRequestDTO dto) {
        return userService.update(idUser, dto);
    }

    @Operation(
            summary = "Exclui um usuário pelo seu ID.",
            description = "Exclui um único usuário por vez na base de dados, desde que o seu ID exista. Exige privilégios de ADMINISTRADOR."
    )
    @DeleteMapping("/user/v1/delete/{idUser}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long idUser) {
        userService.delete(idUser);
    }

    @Operation(
            summary = "Atualiza a senha de um usuário pelo seu ID.",
            description = "Atualiza a senha de um único usuário por vez na base de dados, desde que o seu ID exista, sendo necessário informar a senha antiga. Exige privilégios de ADMINISTRADOR."
    )
    @PatchMapping("/user/v1/update-password/{idUser}")
    @ResponseStatus(HttpStatus.OK)
    public void updatePassword(@PathVariable Long idUser, @Valid @RequestBody UserUpdatePasswordRequestDTO dto) {
        userService.updatePassword(idUser, dto);
    }

    @Operation(
            summary = "Atualiza a função de um usuário pelo seu ID.",
            description = "Atualiza a função um único usuário por vez na base de dados, desde que o seu ID exista, sendo necessãrio informar apenas a função requerida. Exige privilégios de ADMINISTRADOR."
    )
    @PatchMapping("/user/v1/update-role/{idUser}")
    @ResponseStatus(HttpStatus.OK)
    public void updatePassword(@PathVariable Long idUser, @Valid @RequestBody UserUpdateRoleRequestDTO dto) {
        userService.updateRole(idUser, dto);
    }
}
