package org.mongo.blognoticias;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.mongo.blognoticias.Controllers.VistaController;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("FXML/Noticias.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 700);
        stage.setTitle("Blog Noticias");
        stage.setScene(scene);
        stage.show();
        VistaController.setStage(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}