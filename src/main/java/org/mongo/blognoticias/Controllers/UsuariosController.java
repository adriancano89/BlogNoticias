package org.mongo.blognoticias.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import org.mongo.blognoticias.Models.Direccion;
import org.mongo.blognoticias.Models.Usuario;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class UsuariosController implements Initializable {
    private Usuario modeloUsuario = new Usuario();
    @FXML private Text tituloLogin;
    @FXML private Text textoRegistrarse;
    @FXML private GridPane camposRegistrarse;
    @FXML private Button btnIniciarSesion;
    @FXML private Button btnRegistrarse;
    @FXML private TextField campoUsuario;
    @FXML private TextField campoCuentaTwitter;
    @FXML private TextField campoNombre;
    @FXML private TextArea campoDescripcion;
    @FXML private TextField campoTelefonos;
    @FXML private TextField campoCalle;
    @FXML private TextField campoNumero;
    @FXML private TextField campoCiudad;
    @FXML private TextField campoCodigoPostal;

    public void mostrarAlertaError(String mensaje) {
        Alert alertaError = new Alert(Alert.AlertType.WARNING);
        alertaError.setTitle("Atención");
        alertaError.setHeaderText("ATENCIÓN");
        alertaError.setContentText(mensaje);
        alertaError.showAndWait();
    }

    public void mostrarMensajeExitoso(String mensaje) {
        Alert alertaExitosa = new Alert(Alert.AlertType.INFORMATION);
        alertaExitosa.setTitle("");
        alertaExitosa.setHeaderText("EXITOSO");
        alertaExitosa.setContentText(mensaje);
        alertaExitosa.showAndWait();
    }

    public void registrarUsuario(Usuario usuarioInsertar) {
        modeloUsuario.registrarUsuario(usuarioInsertar);
        mostrarMensajeExitoso("Has sido registrado con éxito");
        VistaController.redirigir("Noticias");
    }

    public void iniciarSesion(String usuario) {
        Usuario usuarioValidar = new Usuario(usuario);
        Usuario usuarioValidado = modeloUsuario.verificarUsuario(usuarioValidar);
        if (usuarioValidado != null) {
            VistaController.redirigir("Noticias");
        }
        else {
            mostrarAlertaError("Usuario no registrado");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (tituloLogin != null) {
            camposRegistrarse.setManaged(false);
            btnRegistrarse.setManaged(false);
            btnIniciarSesion.setOnAction(event -> {
                iniciarSesion(campoUsuario.getText());
            });
            textoRegistrarse.setOnMouseClicked((MouseEvent event) -> {
                tituloLogin.setText("REGISTRARSE");
                camposRegistrarse.setVisible(true);
                camposRegistrarse.setManaged(true);

                btnIniciarSesion.setVisible(false);
                btnIniciarSesion.setManaged(false);
                btnRegistrarse.setVisible(true);
                btnRegistrarse.setManaged(true);

                textoRegistrarse.setVisible(false);
                textoRegistrarse.setManaged(false);
            });
            btnRegistrarse.setOnAction(event -> {
                String[] arrayTelefonos = campoTelefonos.getText().split(" ");
                ArrayList<String> telefonos = new ArrayList<>(Arrays.asList(arrayTelefonos));
                Usuario usuarioInsertar = new Usuario(campoUsuario.getText(), campoCuentaTwitter.getText(), campoNombre.getText(), campoDescripcion.getText(), telefonos, new Direccion(campoCalle.getText(), campoNumero.getText(), campoCiudad.getText(), campoCodigoPostal.getText()));
                registrarUsuario(usuarioInsertar);
            });
        }
    }
}
