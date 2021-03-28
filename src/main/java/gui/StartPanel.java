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
    private TableView<?> table2;

    @FXML
    private TableColumn<?, ?> tabName;

    @FXML
    private TableColumn<?, ?> tab2Weight;

    @FXML
    private TableColumn<?, ?> tab2Value;

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
            backpack = backpack.createPopulation(file);
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
        backpack = backpack.createPopulation();
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
            backpack.createFile(file);
        }


    }

    private void loadToTab1() {
        ObservableList<Item> list = FXCollections.observableArrayList(backpack.getItemList());
        tab1Name.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getNazwa()));
        tab1Weight.setCellValueFactory(param -> new ReadOnlyStringWrapper(String.valueOf(param.getValue().getWaga())));
        tab1Value.setCellValueFactory(param -> new ReadOnlyStringWrapper(String.valueOf(param.getValue().getWartosc())));

        tab1WeightField.setText(String.valueOf(backpack.getAktualnawaga()));
        table1.setItems(list);
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
