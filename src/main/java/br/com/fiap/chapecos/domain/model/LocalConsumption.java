package br.com.fiap.chapecos.domain.model;

public enum LocalConsumption {

    RESTAURANTE("Restaurante");

    private final String name;

    LocalConsumption(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
