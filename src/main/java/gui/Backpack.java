package gui;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Backpack {
    private final double wagaPlecaka;
    private final double aktualnawaga;
    private ArrayList<Item> itemList;

    public Backpack() {
        itemList = new ArrayList<>();
        this.wagaPlecaka = 20;
        this.aktualnawaga = 0;
    }

    private Backpack( double aktualnawaga) {
        this.itemList = itemList;
        this.wagaPlecaka = 20;
        this.aktualnawaga = aktualnawaga;
    }


}
