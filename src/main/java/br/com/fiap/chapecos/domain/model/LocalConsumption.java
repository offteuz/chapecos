package br.com.fiap.chapecos.model;

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
