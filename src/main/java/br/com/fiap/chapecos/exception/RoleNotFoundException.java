package br.com.fiap.chapecos.exception;

public class RoleNotFoundException extends RuntimeException{

    public RoleNotFoundException(String message) {
        super(message);
    }

    public RoleNotFoundException() {
        super("Papel de usuário não encontrado. Verifique!");
    }
}
