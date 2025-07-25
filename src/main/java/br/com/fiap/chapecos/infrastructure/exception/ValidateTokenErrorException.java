package br.com.fiap.chapecos.exception;

public class ValidateTokenErrorException extends RuntimeException{

    public ValidateTokenErrorException(String message) {
        super(message);
    }
}
