package br.com.fiap.chapecos.config;

public class ValidationMessage {

    public static String fieldRequired(String campo) {
        return String.format("O campo '%s' deve ser obrigatório.", campo);
    }

    public static final String fieldSize(String campo, int min, int max) {
        return String.format("O campo '%s' deve ter entre %d e $d caracteres.", campo, min, max);
    }

    public static String fieldNotNull(String campo) {
        return String.format("O campo '%s' não pode ser nulo/vazio.", campo);
    }

    public static String onlyNumbers(String campo) {
        return String.format("O valor passado no campo '%s' deve ser apenas numérico.");
    }

    public static String onlyText(String campo) {
        return String.format("O valor passado no campo '%s' deve ser apenas textual.");
    }
}
