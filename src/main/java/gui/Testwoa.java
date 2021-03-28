package gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Testwoa {
    int ilosc_rozwiazan = 9;





    public static void main(String[] args) {
 /*       Plecak plecak1 = new Plecak(20);
        List<Przedmiot> przedmiots = new ArrayList<>();
        Random generator = new Random();
        for (int e = 0; e < 90; e++) {
            System.out.println(e + "\n");
            for (int i = 0; i < 10; i++) {
                przedmiots.add(new Przedmiot(generator.nextInt(20 - 1) + 1, Math.round(generator.nextDouble() * (10D - 1D) + 1D), "Item" + i));

            }
            System.out.println("\n");
            for (Przedmiot przedmiot : przedmiots) {
                System.out.println(przedmiot.getNazwa() + " " + przedmiot.getWaga() + " " + przedmiot.getWartosc());
            }
            przedmiots = new ArrayList<>();
        }
*/
        for (int e = 0; e < 90; e++) {
            Random generator = new Random();
            double v = generator.nextDouble() * (10D - 1D) + 1D;


            System.out.println(Math.round(v * 100.0) / 100.0);
        }

    }
}
