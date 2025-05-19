package br.com.fiap.chapecos.dto.request;

public record UserLoginRequestDTO(

        String identifier,

        String password
) {
}
