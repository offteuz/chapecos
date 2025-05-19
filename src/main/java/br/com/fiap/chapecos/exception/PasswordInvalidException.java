package br.com.fiap.chapecos.exception;

public class PasswordInvalidException extends IllegalArgumentException{

    public PasswordInvalidException(String message) {
        super(message);
    }
}
