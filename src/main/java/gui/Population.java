package gui;

import lombok.Getter;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Getter
public class Population {
    private final List<Item> itemList;


    public Population(int numberOfPopulation) {
        List<Item> itemList2 = new ArrayList<>();
        for (int i = 0; i < numberOfPopulation; i++) {
            itemList2.add(createItem(i));
        }
        this.itemList = itemList2;
    }


    public Population(File file) {
        this.itemList = readPopulationFromFile(file);
    }

    private static String getString(List<Item> itemList) {
        StringBuilder s = new StringBuilder();
        for (Item item : itemList)
            s.append(item.getWeight()).append(" ");
        s.append("\n");
        for (Item item : itemList)
            s.append(item.getValue()).append(" ");

        return s.toString();
    }


    private List<Item> readPopulationFromFile(File file) {
        BufferedReader bufferedReader;
        List<Double> listOfWeight = null;
        List<Integer> listOfValue = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            String s = bufferedReader.readLine();
            listOfWeight = Arrays.stream(s.split(" "))
                    .map(Double::valueOf)
                    .collect(Collectors.toList());
            s = bufferedReader.readLine();
            listOfValue = Arrays.stream(s.split(" "))
                    .map(Integer::valueOf)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (listOfWeight != null && listOfValue != null) {
            List<Item> itemList2 = new ArrayList<>();
            for (int i = 0; i < listOfWeight.size(); i++) {
                itemList2.add(
                        new Item(listOfValue.get(i), listOfWeight.get(i), i));
            }
            return itemList2;
        }

        return null;

    }

    public double getWeightPopulation() {
        return itemList.stream().mapToDouble(Item::getWeight).sum();
    }

    public int getValuePopulation() {
        return itemList.stream().mapToInt(Item::getValue).sum();
    }

    private Item createItem(int itemNumber) {
        Random generator = new Random();
        double v = generator.nextDouble() * (10D - 1D) + 1D;
        //Math.round(v * 100.0) / 100.0,
        return new Item(generator.nextInt(20 - 1) + 1, Math.round(v), itemNumber);
    }

    private String populationToString() {
        return getString(itemList);
    }

    public void populationToFIle(File file) {
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            byte[] strToBytes = populationToString().getBytes();
            outputStream.write(strToBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
