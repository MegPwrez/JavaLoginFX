package org.gregoryjeronimo.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.gregoryjeronimo.model.Usuario;

public class EmpleadoDashboradController implements Initializable {

    @FXML
    private Label lblBienvenida; 

    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
    }    

   
    public void iniciarUsuario(Usuario usuario) {
       
        
     
        if (usuario != null && lblBienvenida != null) {
            lblBienvenida.setText("Bienvenida(o) " + usuario.getUsername());
        }
    }
}
