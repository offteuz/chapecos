package br.com.fiap.chapecos.infrastructure.exception;

public class GenerateTokenErrorException extends RuntimeException{

    public GenerateTokenErrorException(String message) {
        super(message);
    }
}
