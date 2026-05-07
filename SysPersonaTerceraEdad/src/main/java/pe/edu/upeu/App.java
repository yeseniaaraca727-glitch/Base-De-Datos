package pe.edu.upeu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pe.edu.upeu.Config.DatabaseConnection;

public class App extends Application{

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/view/main_persona.fxml"));
        Scene scene = new Scene(loader.load(), 980, 650);
        stage.setTitle("Sistema - Personas de la Tercera Edad");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        DatabaseConnection.getConnection();
        launch(args);

    }
}