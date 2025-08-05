package br.com.fiap.chapecos.infrastructure.exception;

public class PasswordInvalidException extends IllegalArgumentException{

    public PasswordInvalidException(String message) {
        super(message);
    }
}
