package br.com.fiap.chapecos.infrastructure.exception;

public class InvalidScheduleException extends RuntimeException{

    public InvalidScheduleException(String message) {
        super(message);
    }

    public InvalidScheduleException() {
        super("Horário de funcionamento inválido. Verifique!");
    }
}
