package br.com.fiap.chapecos.infrastructure.exception;

public class RegistrationTimeNotFoundException extends RuntimeException{

    public RegistrationTimeNotFoundException(String message) {
        super(message);
    }

    public RegistrationTimeNotFoundException() {
        super("Registro de tempo não encontrado. Verifique!");
    }
}
