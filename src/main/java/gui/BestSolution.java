package gui;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class BestSolution {
    private final List<Item> populationItemList;
    private final double weight;
    private final double value;
    private final ArrayList<Item> itemList = new ArrayList<>();
    private ArrayList<Integer> bestBinary;

    public BestSolution(List<Item> populationItemList, double weight, double value, ArrayList<Integer> list) {
        this.populationItemList = populationItemList;
        this.weight = weight;
        this.value = value;
        bestBinary = list;
        toItemList();
    }


    public void toItemList() {
        ArrayList<Item> bestItems = new ArrayList<>();

        for (int i = 0; i < bestBinary.size(); i++)
        {
            if (bestBinary.get(i) == 1) {
                itemList.add(populationItemList.get(i));
            }
        }
    }






}
