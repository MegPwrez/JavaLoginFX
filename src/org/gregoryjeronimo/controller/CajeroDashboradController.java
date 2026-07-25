package org.gregoryjeronimo.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.gregoryjeronimo.model.Usuario;

/**
 * FXML Controller class para el Panel del Cajero.
 *
 * @author Gregory Jeronimo
 */
public class CajeroDashboradController implements Initializable {

    @FXML
    private Label lblBienvenida; 
    @FXML
    private Button btnSalir;

    private Usuario usuarioSesion;

    /**
     * Inicializa la clase del controlador.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    /**
     * @param usuario Objeto usuario proveniente de la base de datos
     */
    public void iniciarUsuario(Usuario usuario) {
        this.usuarioSesion = usuario;
        
        if (lblBienvenida != null && usuario != null) {
            lblBienvenida.setText("Bienvenida(o) Cajero: " + usuario.getUsername());
        }
        
        System.out.println("Sesión iniciada con éxito en módulo de Cajero: " + usuario.getUsername());
    }

    /**
     * Cierra la ventana actual y abre nuevamente la vista de Inicio de Sesión.
     * 
     * @param event Evento generado al presionar el botón salir
     */
    @FXML
    public void cerrarSesion(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/gregoryjeronimo/view/InicioSesionView.fxml"));
            Parent root = loader.load();

            Stage stageLogin = new Stage();
            stageLogin.setScene(new Scene(root));
            stageLogin.setTitle("Inicio de Sesión");
            stageLogin.show();

            // Obtiene la ventana actual a través del botón y la cierra
            Stage escenaActual = (Stage) btnSalir.getScene().getWindow();
            escenaActual.close();
            
        } catch (IOException e) {
            System.err.println("Error al regresar al inicio de sesión: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
