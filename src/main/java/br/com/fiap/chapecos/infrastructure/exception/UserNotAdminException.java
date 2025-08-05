package br.com.fiap.chapecos.infrastructure.exception;

public class UserNotAdminException extends RuntimeException{

    public UserNotAdminException(String message) {
        super(message);
    }

    public UserNotAdminException() {
        super("O Usuário não é um administrador. Verifique!");
    }
}
