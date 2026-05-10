package pe.edu.upeu;

import io.micronaut.context.ApplicationContext;
import io.micronaut.runtime.Micronaut;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JavaFxApp extends Application {

    private ApplicationContext context;
    private Parent parent;

    @Override
    public void init() throws Exception {
        context = Micronaut.build(getParameters().getRaw().toArray(new String[0]))
                .mainClass(JavaFxApp.class).start();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/maingui.fxml"));
        loader.setControllerFactory(clasz -> context.getBean(clasz));
        parent = loader.load();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Sistema Personas Tercera Edad");
        primaryStage.setResizable(true);
        primaryStage.show();
    }
}
