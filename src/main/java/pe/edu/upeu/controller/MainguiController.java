package pe.edu.upeu.controller;

import io.micronaut.context.ApplicationContext;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

@Singleton
public class MainguiController {

    @FXML
    private AnchorPane contentPane;

    @Inject
    ApplicationContext context;

    @FXML
    public void abrirPersonas(ActionEvent event) {
        abrirVista("/view/main_persona.fxml");
    }

    @FXML
    public void salir(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    private void abrirVista(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            loader.setControllerFactory(context::getBean);
            Parent root = loader.load();

            AnchorPane.setTopAnchor(root, 0.0);
            AnchorPane.setBottomAnchor(root, 0.0);
            AnchorPane.setLeftAnchor(root, 0.0);
            AnchorPane.setRightAnchor(root, 0.0);

            contentPane.getChildren().setAll(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
