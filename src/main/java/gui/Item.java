package gui;

import lombok.Getter;

@Getter
public class Item {
    private final int value;
    private final double weight;
    private final String name;

    public Item(int value, double weight, int numberItem) {
        this.value = value;
        this.weight = weight;
        this.name = "Item: " + numberItem;
    }
}
