package br.com.fiap.chapecos.domain.model;

public enum KitchenType {

    // --- Categoria: Cozinhas Brasileiras ---
    COMIDA_MINEIRA("Comida Mineira", KitchenCategory.COZINHA_BRASILEIRA),
    CHURRASCARIA("Churrascaria", KitchenCategory.COZINHA_BRASILEIRA),
    COMIDA_NORDESTINA("Comida Nordestina", KitchenCategory.COZINHA_BRASILEIRA),
    COMIDA_BAIANA("Comida Baiana", KitchenCategory.COZINHA_BRASILEIRA),
    COMIDA_GAUCHA("Comida Gaúcha", KitchenCategory.COZINHA_BRASILEIRA),
    COMIDA_CAPIXABA("Comida Capixaba", KitchenCategory.COZINHA_BRASILEIRA),
    COMIDA_AMAZONICA("Comida Amazônica", KitchenCategory.COZINHA_BRASILEIRA),

    // --- Categoria: Cozinhas Internacionais ---
    ITALIANA("Comida Italiana", KitchenCategory.COZINHA_INTERNACIONAL),
    JAPONESA("Comida Japonesa", KitchenCategory.COZINHA_INTERNACIONAL),
    CHINESA("Comida Chinesa", KitchenCategory.COZINHA_INTERNACIONAL),
    ARABE("Comida Árabe", KitchenCategory.COZINHA_INTERNACIONAL),
    MEXICANA("Comida Mexicana", KitchenCategory.COZINHA_INTERNACIONAL),
    FRANCESA("Comida Francesa", KitchenCategory.COZINHA_INTERNACIONAL),
    PERUANA("Comida Peruana", KitchenCategory.COZINHA_INTERNACIONAL),
    INDIANA("Comida Indiana", KitchenCategory.COZINHA_INTERNACIONAL),
    TAILANDESA("Comida Tailandesa", KitchenCategory.COZINHA_INTERNACIONAL),
    PORTUGUESA("Comida Portuguesa", KitchenCategory.COZINHA_INTERNACIONAL),
    ALEMA("Comida Alemã", KitchenCategory.COZINHA_INTERNACIONAL),

    // --- Categoria: Tipos Específicos ---
    PIZZARIA("Pizzaria", KitchenCategory.TIPO_ESPECIFICO),
    HAMBURGUERIA("Hamburgueria", KitchenCategory.TIPO_ESPECIFICO),
    SELF_SERVICE("Self-Service / Comida a Quilo", KitchenCategory.TIPO_ESPECIFICO),
    RODIZIO("Rodízio", KitchenCategory.TIPO_ESPECIFICO),
    FRUTOS_DO_MAR("Frutos do Mar", KitchenCategory.TIPO_ESPECIFICO),
    ESPETARIA("Espetaria", KitchenCategory.TIPO_ESPECIFICO),
    VEGETARIANO("Vegetariano / Vegano", KitchenCategory.TIPO_ESPECIFICO),
    CONTEMPORANEO("Contemporâneo / Alta Gastronomia", KitchenCategory.TIPO_ESPECIFICO),
    FAST_FOOD("Fast Food", KitchenCategory.TIPO_ESPECIFICO),
    BISTRO("Bistrô", KitchenCategory.TIPO_ESPECIFICO),
    FOOD_TRUCK("Food Truck / Comida de Rua", KitchenCategory.TIPO_ESPECIFICO),
    PASTELARIA("Pastelaria", KitchenCategory.TIPO_ESPECIFICO),
    LANCHONETE("Lanchonete", KitchenCategory.TIPO_ESPECIFICO),
    CASA_DE_CHA("Casa de Chá", KitchenCategory.TIPO_ESPECIFICO),
    BAR_E_BOTECO("Bar & Boteco", KitchenCategory.TIPO_ESPECIFICO),

    // --- Categoria: Sobremesas ---
    PADARIA("Padaria", KitchenCategory.SOBREMESAS),
    CAFE("Cafeteria", KitchenCategory.SOBREMESAS),
    SORVETERIA("Sorveteria", KitchenCategory.SOBREMESAS),
    ACAI("Açaí", KitchenCategory.SOBREMESAS),
    DOCERIA("Doceria / Confeitaria", KitchenCategory.SOBREMESAS);

    private final String name;
    private final KitchenCategory categoryKitchen;

    KitchenType(String name, KitchenCategory categoryKitchen) {
        this.name = name;
        this.categoryKitchen = categoryKitchen;
    }

    public String getName() {
        return name;
    }

    public KitchenCategory getCategoryKitchen() {
        return categoryKitchen;
    }
}
