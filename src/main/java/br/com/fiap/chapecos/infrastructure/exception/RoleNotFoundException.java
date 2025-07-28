package br.com.fiap.chapecos.exception;

public class RoleNotFoundException extends RuntimeException{

    public RoleNotFoundException() {
        super("Função de usuário não encontrada. Verifique!");
    }

    public RoleNotFoundException(String message) {
        super(message);
    }
}
