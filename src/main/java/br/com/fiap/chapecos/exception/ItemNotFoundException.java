package br.com.fiap.chapecos.exception;

public class ItemNotFoundException extends RuntimeException{

    public ItemNotFoundException(String message) {
        super(message);
    }

    public ItemNotFoundException() {
        super("Item não encontrado. Verifique!");
    }
}
