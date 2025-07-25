package br.com.fiap.chapecos.exception;

public class EstablishmentNotFoundException extends RuntimeException{

    public EstablishmentNotFoundException(String message) {
        super(message);
    }

    public EstablishmentNotFoundException() {
        super("Estabelecimento não encontrado. Verifique");
    }
}
