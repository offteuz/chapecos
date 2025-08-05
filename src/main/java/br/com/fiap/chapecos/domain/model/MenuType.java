package br.com.fiap.chapecos.domain.model;

public enum MenuType {

    ENTRADA("Entrada"),

    PRINCIPAL("Principal"),

    SOBREMESA("Sobremesa");

    private final String name;

    MenuType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
