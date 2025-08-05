package br.com.fiap.chapecos.infrastructure.exception;

public class MenuNotFoundException extends RuntimeException{

    public MenuNotFoundException(String message) {
        super(message);
    }

    public MenuNotFoundException() {
        super("Cardápio não encontrado. Verifique!");
    }
}
