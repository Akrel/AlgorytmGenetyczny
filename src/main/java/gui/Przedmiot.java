package gui;

import lombok.Getter;

@Getter
public class Przedmiot {
    private final int wartosc;
    private final double waga;
    private final String nazwa;

    public Przedmiot(int wartosc, double waga, String nazwa) {
        this.wartosc = wartosc;
        this.waga = waga;
        this.nazwa = nazwa;
    }
}
