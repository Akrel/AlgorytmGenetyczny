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
        this.wagaPlecaka = 20;
        this.aktualnawaga = 0;
    }

    private Backpack(List<Item> itemList, double aktualnawaga) {
        this.itemList = itemList;
        this.wagaPlecaka = 20;
        this.aktualnawaga = aktualnawaga;
    }

    public void createPopulation() {
        for (int i = 0; i < 10; i++) {
            Item item = createRandomItem(i);
            itemList.add(item);
            aktualnawaga += item.getWaga();
        }
    }


    public Backpack createPopulation(File file) {
        BufferedReader bufferedReader = null;
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
        if (!listOfValue.isEmpty() && !listOfWeight.isEmpty()) {
            List<Item> itemList2 = new ArrayList<>();
            double aktualnawaga = 0;
            for (int i = 0; i < listOfWeight.size(); i++) {
                Item item = createItem(listOfWeight.get(i), listOfValue.get(i), i);
                itemList2.add(createRandomItem(i));
                aktualnawaga += item.getWaga();
            }
            return new Backpack(itemList2, aktualnawaga);
        }
        return null;
    }


    private Item createRandomItem(int nameItem) {
        Random generator = new Random();
        return new Item(generator.nextInt(20 - 1) + 1, Math.round(generator.nextDouble() * (10D - 1D) + 1D), "Item" + nameItem);
    }

    private Item createItem(double weight, int value, int nameItem) {
        return new Item(value, weight, "Item" + nameItem);
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
