
package org.gregoryjeronimo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Principal extends Application {

    @Override
    public void start(Stage escenarioPrincipal) throws Exception {

        Parent raiz = FXMLLoader.load(
                getClass().getResource("/org/gregoryjeronimo/view/InicioSesionView.fxml"));

        Scene escena = new Scene(raiz);

        escenarioPrincipal.setTitle("Inicio de Sesión");
        escenarioPrincipal.setScene(escena);
        escenarioPrincipal.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}