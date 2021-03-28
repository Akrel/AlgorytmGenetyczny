package gui;

import lombok.Getter;

@Getter
public class Item {
    private final int wartosc;
    private final double waga;
    private final String nazwa;

    public Item(int wartosc, double waga, String nazwa) {
        this.wartosc = wartosc;
        this.waga = waga;
        this.nazwa = nazwa;
    }
}
