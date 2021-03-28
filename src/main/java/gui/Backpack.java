package gui;

import lombok.Getter;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Getter
public class Backpack {
    private List<Item> itemList;
    private double wagaPlecaka;
    private double aktualnawaga;

    public Backpack() {
        itemList = new ArrayList<>();
        this.wagaPlecaka = 0;
        this.aktualnawaga = 0;
    }


    public void createPopulation() {
        for (int i = 0; i < 10; i++) {
            itemList.add(createItem(i));
        }
    }
    public void createPopulation(List<Double> listOfWeight,List<Integer> listOfValue) {
        for (int i = 0; i < 10; i++) {
            itemList.add(createItem(i));
        }
    }

    public void createPopulation(File file) {
        BufferedReader bufferedReader = null;
        try {
            //TODO czy trzeba zmaknac
            bufferedReader = new BufferedReader(new FileReader(file));
            String s = bufferedReader.readLine();
            List<Double> listOfWeight = Arrays.stream(s.split(" "))
                    .map(Double::valueOf)
                    .collect(Collectors.toList());
            s = bufferedReader.readLine();
            List<Integer> listOfValue = Arrays.stream(s.split(" "))
                    .map(Integer::valueOf)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private Item createItem(int nameItem) {
        Random generator = new Random();
        return new Item(generator.nextInt(20 - 1) + 1, Math.round(generator.nextDouble() * (10D - 1D) + 1D), "Item" + nameItem);
    }

    public String populationToString() {
        StringBuilder s = new StringBuilder();
        for (Item item : itemList)
            s.append(item.getWaga()).append(" ");
        s.append("\n");
        for (Item item : itemList)
            s.append(item.getWartosc()).append(" ");

        return s.toString();
    }


    public void createFile(File file) {
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            byte[] strToBytes = populationToString().getBytes();
            outputStream.write(strToBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
