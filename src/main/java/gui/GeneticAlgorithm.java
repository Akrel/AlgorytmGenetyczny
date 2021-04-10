package gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeneticAlgorithm {

    private final ArrayList<ArrayList<Integer>> chromosom;
    private final ArrayList<Integer> valueList;
    private ArrayList<Double> weightList;
    private Population population;


    public GeneticAlgorithm(Population population) {
        this.population = population;
        chromosom = new ArrayList<>();
        valueList = new ArrayList<>();
        weightList = new ArrayList<>();
    }

    //TODO zmiana nazwy chromosom
    public void createPopulation() {
        Random random = new Random();

        ArrayList<Integer> list;
        for (int k = 0; k < 10; k++) {
            list = new ArrayList<>();
            for (int i = 0; i < population.getItemList().size(); i++) {
                list.add(random.nextInt(2));
            }
            System.out.println("\n");
            this.chromosom.add(list);
        }

        fittest();
        System.out.println("dsa");
    }

    public void fittest() {
        List<Item> populationItemList = population.getItemList();
        double wagaPlecaka = 20;
        double totalWeight;
        int totalValue;
        for (ArrayList<Integer> listItemBit : chromosom) {

            do {
                totalWeight = 0;
                totalValue = 0;
                for (int i = 0; i < listItemBit.size(); i++) {
                    if (listItemBit.get(i) == 1) {
                        Item item = populationItemList.get(i);
                        totalValue += item.getValue();
                        totalWeight += item.getWeight();
                    }
                }
                if (totalWeight > wagaPlecaka) {
                    Random random = new Random();
                    int position;
                    do {
                        position = random.nextInt(listItemBit.size());
                    } while (listItemBit.get(position) == 0);
                    listItemBit.set(position, 0);
                } else {
                    weightList.add(totalWeight);
                    valueList.add(totalValue);
                }
            } while (totalWeight > wagaPlecaka);


        }


    }


}
