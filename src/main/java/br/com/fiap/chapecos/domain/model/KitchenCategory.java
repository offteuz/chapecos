package br.com.fiap.chapecos.model;

public enum KitchenCategory {

    COZINHA_BRASILEIRA("Cozinha Brasileira"),
    COZINHA_INTERNACIONAL("Cozinha Internacional"),
    TIPO_ESPECIFICO("Tipo Específico"),
    SOBREMESAS("Sobremesas");

    private final String name;

    KitchenCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
