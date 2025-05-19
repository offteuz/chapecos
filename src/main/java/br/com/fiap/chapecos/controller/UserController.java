package br.com.fiap.chapecos.controller;

import br.com.fiap.chapecos.dto.request.UserRequestDTO;
import br.com.fiap.chapecos.dto.request.UserUpdatePasswordRequestDTO;
import br.com.fiap.chapecos.dto.response.UserResponseDTO;
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

    @GetMapping("/find-all/v0/user")
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponseDTO> findAll() {
        return userService.findAll();
    }

    @GetMapping("/find-by-id/v0/user/{idUser}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDTO findById(@PathVariable Long idUser) {
        return userService.findById(idUser);
    }

    @PutMapping("/update/v0/user/{idUser}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDTO update(@PathVariable Long idUser, @Valid @RequestBody UserRequestDTO dto) {
        return userService.update(idUser, dto);
    }

    @DeleteMapping("/delete/v0/user/{idUser}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long idUser) {
        userService.delete(idUser);
    }

    @PatchMapping("/update-password/v0/user/{idUser}")
    @ResponseStatus(HttpStatus.OK)
    public void updatePassword(@PathVariable Long idUser, @Valid @RequestBody UserUpdatePasswordRequestDTO dto) {
        userService.updatePassword(idUser, dto);
    }
}
