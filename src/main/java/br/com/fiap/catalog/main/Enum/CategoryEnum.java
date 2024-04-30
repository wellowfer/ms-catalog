package br.com.fiap.catalog.main.Enum;

import java.util.HashMap;
import java.util.Map;

public enum CategoryEnum {
    SNACK(1, "Lanche"),
    DESSERT(2, "Sobremesa"),
    DRINK(3, "Bebida"),
    SIDE_DISHES(4, "Acompanhamento");

    private final int sequence;
    private final String description;
    private static final Map<Integer, CategoryEnum> sequenceMap = new HashMap<>();
    private static final Map<String, CategoryEnum> descriptionMap = new HashMap<>();

    static {
        for (CategoryEnum category : CategoryEnum.values()) {
            sequenceMap.put(category.sequence, category);
            descriptionMap.put(category.description.toLowerCase(), category);
        }
    }

    CategoryEnum(int sequence, String description) {
        this.sequence = sequence;
        this.description = description;
    }

    public int getSequence() {
        return sequence;
    }

    public String getDescription() {
        return description;
    }

    public static CategoryEnum getBySequence(int sequence) {
        return sequenceMap.get(sequence);
    }

    public static CategoryEnum getByDescription(String description) {
        return descriptionMap.get(description.toLowerCase());
    }
}