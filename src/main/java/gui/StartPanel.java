package gui;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class StartPanel implements Initializable {
    @FXML
    private AnchorPane anchorMain;
    private Backpack backpack;
    private Population population;
    @FXML
    private TableView<Item> table1;

    @FXML
    private TableColumn<Item, String> tab1Name;

    @FXML
    private TableColumn<Item, String> tab1Weight;

    @FXML
    private TableColumn<Item, String> tab1Value;

    @FXML
    private TextArea tab1ValueField;

    @FXML
    private TextArea tab1WeightField;

    @FXML
    private TableView<Item> table2;

    @FXML
    private TableColumn<Item, String> tabName2;

    @FXML
    private TableColumn<Item, String> tab2Weight;

    @FXML
    private TableColumn<Item, String> tab2Value;
    @FXML
    private TextArea bestKnapsackValue;

    @FXML
    private TextArea bestKnapsackWeight;


    @FXML
    void readFromFile(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Wybierz plik: ");
        File file = null;
        try {
            file = fileChooser.showOpenDialog(getWindow());
        } catch (Exception e) {
            System.out.println("brak danych");
        }

        if (file != null) {
            population = new Population(file);
            System.out.println("dsa");
            clearTable();
            loadToTab1();
        }
    }

    private Stage getWindow() {
        return (Stage) anchorMain.getScene().getWindow();
    }


    @FXML
    void generatePopulation() {
        population = new Population(10);
        clearTable();
        loadToTab1();

    }

    @FXML
    void saveToFIle() {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save File");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showSaveDialog(getWindow());
        if (file != null) {
            if (population != null)
                population.populationToFIle(file);
        }


    }

    @FXML
    void runAlg() {
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(population);
        geneticAlgorithm.run();
        loadToTab2(geneticAlgorithm.findBest());

    }

    private void loadToTab1() {
        ObservableList<Item> list = FXCollections.observableArrayList(population.getItemList());
        tab1Name.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getName()));
        tab1Weight.setCellValueFactory(param -> new ReadOnlyStringWrapper(String.valueOf(param.getValue().getWeight())));
        tab1Value.setCellValueFactory(param -> new ReadOnlyStringWrapper(String.valueOf(param.getValue().getValue())));

        tab1WeightField.setText(String.valueOf(population.getWeightPopulation()));
        table1.setItems(list);
    }


    private void loadToTab2(BestSolution best) {
        ObservableList<Item> list = FXCollections.observableArrayList(best.getItemList());
        tabName2.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getName()));
        tab2Weight.setCellValueFactory(param -> new ReadOnlyStringWrapper(String.valueOf(param.getValue().getWeight())));
        tab2Value.setCellValueFactory(param -> new ReadOnlyStringWrapper(String.valueOf(param.getValue().getValue())));

        bestKnapsackValue.setText(String.valueOf(best.getValue()));
        bestKnapsackWeight.setText(String.valueOf(best.getWeight()));
        table2.setItems(list);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        backpack = new Backpack();

    }


    private void clearTable() {
        for (int i = 0; i < table1.getItems().size(); i++) {
            table1.getItems().clear();
        }
    }
}
