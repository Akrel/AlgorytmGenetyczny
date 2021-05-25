package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Start extends Application {
    private AnchorPane rootNode;


    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
            if (!primaryStage.isFullScreen() && event.isShortcutDown() && event.isPrimaryButtonDown()) {

                primaryStage.setFullScreen(true);
                primaryStage.setResizable(true);
                event.consume();
            }
        });


        primaryStage.setScene(new Scene(rootNode));
        primaryStage.show();
    }

    @Override
    public void init() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation((getClass().getResource("/Panel.fxml")));
        rootNode = fxmlLoader.load();
    }
}
