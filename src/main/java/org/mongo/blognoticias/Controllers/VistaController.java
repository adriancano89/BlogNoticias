package org.mongo.blognoticias.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class VistaController {
    private static Stage stage;

    public VistaController() {

    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        VistaController.stage = stage;
    }

    public static void redirigir(String vista) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(VistaController.class.getResource("/org/mongo/blognoticias/FXML/" + vista + ".fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1200, 700);
            getStage().setTitle(vista);
            getStage().setScene(scene);
        } catch (IOException error) {
            System.out.println("Error al cargar la vista: " + error.getMessage());
        }
    }
}
