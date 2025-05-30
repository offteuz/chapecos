package br.com.fiap.chapecos.controller;

import br.com.fiap.chapecos.dto.request.*;
import br.com.fiap.chapecos.dto.response.UserResponseDTO;
import br.com.fiap.chapecos.model.Role;
import br.com.fiap.chapecos.service.UserService;
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

    @GetMapping("/user/v1/find-all")
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponseDTO> findAll() {
        return userService.findAll();
    }

    @GetMapping("/user/v1/find-by-id/{idUser}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDTO findById(@PathVariable Long idUser) {
        return userService.findById(idUser);
    }

    @PutMapping("/user/v1/update/{idUser}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDTO update(@PathVariable Long idUser, @Valid @RequestBody UserUpdateRequestDTO dto) {
        return userService.update(idUser, dto);
    }

    @DeleteMapping("/user/v1/delete/{idUser}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long idUser) {
        userService.delete(idUser);
    }

    @PatchMapping("/user/v1/update-password/{idUser}")
    @ResponseStatus(HttpStatus.OK)
    public void updatePassword(@PathVariable Long idUser, @Valid @RequestBody UserUpdatePasswordRequestDTO dto) {
        userService.updatePassword(idUser, dto);
    }

    @PatchMapping("/user/v1/update-role/{idUser}")
    @ResponseStatus(HttpStatus.OK)
    public void updatePassword(@PathVariable Long idUser, @Valid @RequestBody UserUpdateRoleRequestDTO dto) {
        userService.updateRole(idUser, dto);
    }
}
