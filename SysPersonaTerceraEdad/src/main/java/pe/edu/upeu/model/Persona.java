package pe.edu.upeu.model;

// Importo los enums que voy a usar como tipos de datos controlados
import pe.edu.upeu.enums.EstadoSalud;
import pe.edu.upeu.enums.TipoPension;

// Importo las propiedades de JavaFX para poder enlazar los datos con la interfaz
import javafx.beans.property.*;

public class Persona {

    // Uso propiedades en lugar de variables normales
    // porque JavaFX necesita esto para actualizar automáticamente la tabla (TableView)
    private IntegerProperty id;
    private StringProperty nombreCompleto;
    private IntegerProperty edad;
    private StringProperty curp;
    private StringProperty domicilio;
    private ObjectProperty<TipoPension> tipoPension;
    private ObjectProperty<EstadoSalud> estadoSalud;

    // Constructor vacío
    // Aquí inicializo todas las propiedades para evitar errores de null
    public Persona() {
        this.id = new SimpleIntegerProperty();
        this.nombreCompleto = new SimpleStringProperty();
        this.edad = new SimpleIntegerProperty();
        this.curp = new SimpleStringProperty();
        this.domicilio = new SimpleStringProperty();
        this.tipoPension = new SimpleObjectProperty<>();
        this.estadoSalud = new SimpleObjectProperty<>();
    }

    // Constructor con parámetros
    // Este me permite crear objetos Persona con todos sus datos desde el inicio
    public Persona(int id, String nombreCompleto, int edad, String curp,
                   String domicilio, TipoPension tipoPension, EstadoSalud estadoSalud) {
        this(); // reutilizo el constructor vacío
        setId(id);
        setNombreCompleto(nombreCompleto);
        setEdad(edad);
        setCurp(curp);
        setDomicilio(domicilio);
        setTipoPension(tipoPension);
        setEstadoSalud(estadoSalud);
    }

    // =====================
    // GETTERS Y SETTERS
    // =====================

    // ID
    // Sirve para identificar cada registro de forma única
    public int getId() { return id.get(); }
    public void setId(int id) { this.id.set(id); }
    public IntegerProperty idProperty() { return id; }

    // Nombre completo
    // Guarda el nombre de la persona
    public String getNombreCompleto() { return nombreCompleto.get(); }
    public void setNombreCompleto(String v) { this.nombreCompleto.set(v); }
    public StringProperty nombreCompletoProperty() { return nombreCompleto; }

    // Edad
    // Guarda la edad de la persona (importante para filtros)
    public int getEdad() { return edad.get(); }
    public void setEdad(int v) { this.edad.set(v); }
    public IntegerProperty edadProperty() { return edad; }

    // CURP
    // Documento único de identificación
    public String getCurp() { return curp.get(); }
    public void setCurp(String v) { this.curp.set(v); }
    public StringProperty curpProperty() { return curp; }

    // Domicilio
    // Dirección donde vive la persona
    public String getDomicilio() { return domicilio.get(); }
    public void setDomicilio(String v) { this.domicilio.set(v); }
    public StringProperty domicilioProperty() { return domicilio; }

    // Tipo de pensión
    // Uso enum para mantener valores controlados
    public TipoPension getTipoPension() { return tipoPension.get(); }
    public void setTipoPension(TipoPension v) { this.tipoPension.set(v); }
    public ObjectProperty<TipoPension> tipoPensionProperty() { return tipoPension; }

    // Estado de salud
    // También uso enum para evitar errores en los datos
    public EstadoSalud getEstadoSalud() { return estadoSalud.get(); }
    public void setEstadoSalud(EstadoSalud v) { this.estadoSalud.set(v); }
    public ObjectProperty<EstadoSalud> estadoSaludProperty() { return estadoSalud; }
}
