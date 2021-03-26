package gui;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class StartPanel  {
    @FXML
    private AnchorPane anchorMain;
    @FXML
    void readFromFile(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Wybierz plik: ");
        Stage stage = (Stage) anchorMain.getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);
        System.out.println("a");

    }
}
