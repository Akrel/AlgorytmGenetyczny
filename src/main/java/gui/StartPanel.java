package gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    void readFromFile(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Wybierz plik: ");
        File file = fileChooser.showOpenDialog(getWindow());
        backpack = backpack.createPopulation(file);
        System.out.println("dsa");

    }

    private Stage getWindow() {
        return (Stage) anchorMain.getScene().getWindow();
    }


    @FXML
    void generatePopulation() {
        backpack.createPopulation();

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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        backpack = new Backpack();
    }
}
