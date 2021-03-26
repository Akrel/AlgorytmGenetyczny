package gui;

import gui.Przedmiot;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Plecak {
    private final List<Przedmiot> przedmioty;
    private final double wagaPlecaka;
    private final double aktualnawaga;

    public Plecak(double wagaPlecaka) {
        przedmioty = new ArrayList<Przedmiot>();
        this.wagaPlecaka = wagaPlecaka;
        this.aktualnawaga = 0;
    }


    public int dodajDoPlecaka(Przedmiot przedmiot) {
        if (aktualnawaga < wagaPlecaka) {
            przedmioty.add(przedmiot);
            return 1;
        }
        return 0;
    }

    public void wyswietlPlecak() {
        for (Przedmiot przedmiot : przedmioty) {
            System.out.println(przedmiot.getNazwa() + " " + przedmiot.getWaga() + " " + przedmiot.getWartosc());
        }

    }

}
