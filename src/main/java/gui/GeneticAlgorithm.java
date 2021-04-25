package gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeneticAlgorithm {

    private final List<Item> populationItemList;
    private final Double weightKnapsack = 20.0;
    private final int numberOfItems = 10;
    private ArrayList<ArrayList<Integer>> chromosom;
    private ArrayList<Integer> valueList;
    private ArrayList<Double> weightList;

    public GeneticAlgorithm(Population population) {
        populationItemList = population.getItemList();
    }

    public void createPopulation() {
        chromosom = new ArrayList<>();
        Random random = new Random();
        ArrayList<Integer> list;
        for (int k = 0; k < numberOfItems; k++) {
            do {
                list = new ArrayList<>();
                for (int i = 0; i < populationItemList.size(); i++) {
                    list.add(random.nextInt(2));
                }
            } while (weightFittest(list) < 0);
            this.chromosom.add(list);
        }
    }

    public void test() {
        createPopulation();
        calculateChromosome();
        fittest();

        System.out.println(chromosom);
    }


    private Random randomNumber() {
        return new Random();
    }

    public void fittest() {

        double totalWeight;
        int totalValue;
        for (ArrayList<Integer> listItemBit : chromosom) {

            do {
                totalWeight = 0;
                totalValue = 0;
                for (int i = 0; i < listItemBit.size(); i++) {
                    if (listItemBit.get(i) == 1) {
                        Item item = getItem(i);
                        totalValue += item.getValue();
                        totalWeight += item.getWeight();
                    }
                }
                if (totalWeight > weightKnapsack) {
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
            } while (totalWeight > weightKnapsack);

        }

    }

    private void calculateChromosome() {
        List<Double> weightListLocal = new ArrayList<>();
        List<Integer> valueListLocal = new ArrayList<>();
        for (ArrayList<Integer> listItemBit : chromosom) {
            weightListLocal.add(weightFittest(listItemBit));
        }
    }


    private Item getItem(int i) {
        return populationItemList.get(i);
    }

    private double weightFittest(ArrayList<Integer> list) {
        int totalWeight = 0;
        int i = 0;
        for (Integer listItemBit : list) {
            if (listItemBit == 1) {
                Item item = getItem(i);
                totalWeight += item.getWeight();
            }
            ++i;
        }
        return totalWeight;
    }

    private int valueFittest(ArrayList<Integer> list) {
        int totalValue = 0;
        int i = 0;
        for (Integer listItemBit : list) {
            if (listItemBit == 1) {
                Item item = getItem(i);
                totalValue += item.getValue();
            }
            ++i;
        }
        return totalValue;
    }
}
