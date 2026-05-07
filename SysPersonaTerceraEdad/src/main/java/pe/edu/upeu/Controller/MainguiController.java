package pe.edu.upeu.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MainguiController {
    @FXML private AnchorPane contentPane;
    @FXML public void abrirPersonas() throws IOException{
        AnchorPane vista= FXMLLoader.load(getClass(). getResource("view/main_persona.fxml"));
         contentPane.getChildren().setAll(vista);
         AnchorPane.setTopAnchor(vista, 0.0);
         AnchorPane.setBottomAnchor(vista, 0.0);
         AnchorPane.setLeftAnchor(vista, 0.0);
         AnchorPane.setRightAnchor(vista, 0.0);
    }

}
