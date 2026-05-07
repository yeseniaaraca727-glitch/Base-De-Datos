module pe.edu.upeu {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens pe.edu.upeu to javafx.graphics, javafx.fxml;

    opens pe.edu.upeu.Controller to javafx.fxml;
    opens pe.edu.upeu.model to javafx.base;

    exports pe.edu.upeu;
}