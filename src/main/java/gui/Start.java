package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Start extends Application {
    private AnchorPane rootNode;
    private Parent root;
    private  FXMLLoader fxmlLoader;


    @Override
    public void start(Stage primaryStage) throws Exception {




        Scene scene = new Scene(rootNode, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
        primaryStage.show();
    }


    public static void main(String[] args) {
        Application.launch(args);
    }



    @Override
    public void init() throws IOException {

         fxmlLoader = new FXMLLoader();

        fxmlLoader.setLocation((getClass().getResource("/Panel.fxml")));
        rootNode = fxmlLoader.load();
        System.out.println("dsa");
    }
}
