package org.mongo.blognoticias.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class NoticiasController implements Initializable {
    @FXML private VBox seccionNoticias;
    @FXML private Button btnIniciarSesion;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (seccionNoticias != null) {
            btnIniciarSesion.setOnAction(event -> {
                VistaController.redirigir("Login");
            });
        }
    }
}
