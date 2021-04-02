package gui;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Backpack {
    private final double wagaPlecaka;
    private final double aktualnawaga;
    private List<Item> itemList;

    public Backpack() {
        itemList = new ArrayList<>();
        this.wagaPlecaka = 20;
        this.aktualnawaga = 0;
    }

    private Backpack(List<Item> itemList, double aktualnawaga) {
        this.itemList = itemList;
        this.wagaPlecaka = 20;
        this.aktualnawaga = aktualnawaga;
    }


}
