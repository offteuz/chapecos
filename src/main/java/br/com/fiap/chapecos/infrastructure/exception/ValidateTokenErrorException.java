package br.com.fiap.chapecos.infrastructure.exception;

public class ValidateTokenErrorException extends RuntimeException{

    public ValidateTokenErrorException(String message) {
        super(message);
    }
}
