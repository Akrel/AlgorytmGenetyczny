package gui;

import java.util.*;

public class GeneticAlgorithm {

    private final List<Item> populationItemList;
    private final double weightKnapsack;
    private final int numberOfItems;
    private final int numberOfGenerations;
    private final double mutationRate;
    private final double chanceToCross;
    private ArrayList<ArrayList<Integer>> chromosom;
    private ArrayList<Integer> valueList;
    private ArrayList<Double> weightList;
    private ArrayList<Integer> bestGene;

    public GeneticAlgorithm(Population population, double mutationRate, double chanceToCross, int numberOfGenerations, double weightKnapsack) {
        populationItemList = population.getItemList();
        this.mutationRate = mutationRate;
        this.chanceToCross = chanceToCross;
        this.weightKnapsack = weightKnapsack;
        this.numberOfGenerations = numberOfGenerations;
        numberOfItems = populationItemList.size();
    }

    public void createPopulation() {
        chromosom = new ArrayList<>();
        ArrayList<Integer> list;
        for (int k = 0; k < numberOfItems; k++) {
            do {
                list = new ArrayList<>();
                for (int i = 0; i < populationItemList.size(); i++) {
                    list.add(randomNumber().nextInt(2));
                }
            } while (weightFittest(list) < 0);
            this.chromosom.add(list);
        }
    }

    public BestSolution findBest() {
        ArrayList<Integer> list = null;
        double weight = 0;
        double value = 0;
        ArrayList<Double> weightList = new ArrayList<>();
        ArrayList<Double> valueList = new ArrayList<>();

        for (ArrayList<Integer> e : chromosom) {
            double wei = weightFittest(e);
            double val = valueFittest(e);
            weightList.add(wei);
            valueList.add(val);
            if (wei <= weightKnapsack && val > value) {
                weight = wei;
                value = val;
                list = e;
            }

        }
        return new BestSolution(populationItemList, weight, value, list);
    }


    public void run() {

        createPopulation();
        System.out.println(chromosom);
        for (int k = 0; k < numberOfGenerations; k++) {
            fittest();
            calculateChromosome();
            bestGene = tournamentSelection();
            ArrayList<ArrayList<Integer>> arrayLists = twoCross(bestGene);
            System.out.println();
            int q = 0;
            for (ArrayList<Integer> e : arrayLists) {
                System.out.println(e);
                System.out.println("Tab" + q + " V" + valueFittest(e) + " W" + weightFittest(e) + "\n");
                ++q;
            }


            mutation(arrayLists);

            q = 0;
            for (ArrayList<Integer> e : arrayLists) {
                System.out.println(e);
                System.out.println("Tab" + q + " V" + valueFittest(e) + " W" + weightFittest(e) + "\n");
                ++q;
            }


            Collections.copy(chromosom, arrayLists);
            System.out.println("da");
        }
        System.out.println("SA");
    }

    private void mutation(ArrayList<ArrayList<Integer>> arrayLists) {
        for (int i = 0; i < numberOfItems; i++) {
            if (randomNumber().nextDouble() < mutationRate) {
                System.out.println("mutacja" + i);
                arrayLists.set(i, bitFilpMutation(arrayLists.get(i)));
            }

        }
    }

    public ArrayList<Integer> bitFilpMutation(ArrayList<Integer> chromosome) {
        ArrayList<Integer> newChromosome = new ArrayList<>();

        for (int i = 0; i < chromosome.size(); i++) {
            Integer integer = chromosome.get(i);
            if (integer == 1)
                newChromosome.add(i, 0);
            else
                newChromosome.add(i, 1);
        }
        return newChromosome;
    }


    private void newValueAndWeight() {
        valueList = new ArrayList<>();
        weightList = new ArrayList<>();
    }

    private void childrenPopulation(int numberOfChromosome, ArrayList<ArrayList<Integer>> children) {
        for (int i = 0; i < numberOfChromosome; i++) {
            children.add(new ArrayList<>());
        }
    }

    private ArrayList<ArrayList<Integer>> twoCross(ArrayList<Integer> best) {
        ArrayList<ArrayList<Integer>> children = new ArrayList<>();
        childrenPopulation(populationItemList.size(), children);
        ArrayList<Integer> parent1;
        ArrayList<Integer> parent2;
        ArrayList<Integer> child1;
        ArrayList<Integer> child2;
        Random randomizer = new Random();
        int n = 2;
        for (int i = 0; i < numberOfItems; i += 2) {

            parent1 = chromosom.get(best.get(i));
            child1 = children.get(i);
            if (i < numberOfItems - 1) {
                parent2 = chromosom.get(best.get(i + 1));
                child2 = children.get(i + 1);
            } else {
                parent2 = chromosom.get(best.get(0));
                child2 = children.get(0);
            }

            if (randomizer.nextDouble() < chanceToCross) {
                TreeSet<Integer> indices = new TreeSet<>();
                int iNeeded;
                if (n < numberOfItems) {
                    iNeeded = n;
                } else
                    iNeeded = numberOfItems - 1;
                for (int j = 0; j < iNeeded; j++) {
                    int guess = randomizer.nextInt(numberOfItems - 1);
                    while (indices.contains(guess)) {
                        guess = (guess + 1) % (numberOfItems - 1);
                    }
                    indices.add(guess);
                }

                boolean takeFromPar1 = true;
                int j = 0;
                for (Integer currSlice : indices) {
                    while (j <= currSlice) {
                        j = getJ(parent1, parent2, child1, child2, takeFromPar1, j);
                    }
                    takeFromPar1 = !takeFromPar1;
                }
                while (j < numberOfItems) {
                    j = getJ(parent1, parent2, child1, child2, takeFromPar1, j);
                }
            } else {

                if (child1.isEmpty() && child2.isEmpty()) {
                    child1.addAll(parent1);
                    child2.addAll(parent2);
                } else {
                    child1.clear();
                    child2.clear();
                    child1.addAll(parent1);
                    child2.addAll(parent2);
                }

            }
        }
        return children;
    }

    private int getJ(ArrayList<Integer> parent1, ArrayList<Integer> parent2, ArrayList<Integer> child1, ArrayList<Integer> child2, boolean takeFromPar1, int j) {
        if (takeFromPar1) {
            child1.add(j, parent1.get(j));
            child2.add(j, parent2.get(j));
        } else {
            child1.add(j, parent2.get(j));
            child2.add(j, parent1.get(j));
        }
        j++;
        return j;
    }

    private ArrayList<Integer> tournamentSelection() {
        ArrayList<Integer> best = new ArrayList<>();
        for (int i = 0; i < numberOfItems; i++) {
            int ind1 = randomNumber().nextInt(numberOfItems);
            int ind2 = randomNumber().nextInt(numberOfItems);
            System.out.printf(ind1 + " " + ind2 + "\n");

            int stronger;
            if (valueList.get(ind1) < valueList.get(ind2))
                stronger = 0;
            else if (valueList.get(ind1) > valueList.get(ind2))
                stronger = 1;
            else
                stronger = randomNumber().nextInt(2);

            if (stronger == 1) best.add(i, ind1);
            else best.add(i, ind2);

        }

        return best;
    }

    private Random randomNumber() {
        return new Random();
    }

    public void fittest() {
        newValueAndWeight();
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
            valueListLocal.add(valueFittest(listItemBit));
        }

        System.out.println(weightListLocal);
        System.out.println(valueListLocal);
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
            i++;
        }
        return totalValue;
    }
}
