package br.com.fiap.chapecos.infrastructure.exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException() {
        super("Usuário não encontrado. Verifique!");
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
