package pe.edu.upeu.controller;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import pe.edu.upeu.enums.EstadoSalud;
import pe.edu.upeu.enums.TipoPension;
import pe.edu.upeu.model.Persona;
import pe.edu.upeu.service.PersonaService;

@Singleton
public class PersonaController {

    @FXML private TextField txtNombre, txtEdad, txtCurp, txtDomicilio;
    @FXML private TextField txtEdadMin, txtEdadMax;
    @FXML private ComboBox<TipoPension> cbTipoPension, cbFiltroTipoPension;
    @FXML private ComboBox<EstadoSalud> cbEstadoSalud;
    @FXML private TableView<Persona> tblPersonas;
    @FXML private TableColumn<Persona, Long>       colId;
    @FXML private TableColumn<Persona, String>     colNombre, colCurp, colDomicilio;
    @FXML private TableColumn<Persona, Integer>    colEdad;
    @FXML private TableColumn<Persona, TipoPension> colTipoPension;
    @FXML private TableColumn<Persona, EstadoSalud> colEstadoSalud;
    @FXML private Label lblMensaje;

    @Inject
    PersonaService service;

    // ID de la persona seleccionada para editar/eliminar
    private Long selectedId = null;

    @FXML
    public void initialize() {
        // Configurar columnas de la tabla
        colId.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getId()));
        colNombre.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNombreCompleto()));
        colEdad.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getEdad()));
        colCurp.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getCurp()));
        colDomicilio.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getDomicilio()));
        colTipoPension.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getTipoPension()));
        colEstadoSalud.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getEstadoSalud()));

        // Cargar combos
        cbTipoPension.setItems(FXCollections.observableArrayList(TipoPension.values()));
        cbFiltroTipoPension.setItems(FXCollections.observableArrayList(TipoPension.values()));
        cbEstadoSalud.setItems(FXCollections.observableArrayList(EstadoSalud.values()));

        cargarTabla();

        // Selección en tabla → llenar formulario
        tblPersonas.getSelectionModel().selectedItemProperty().addListener((obs, old, sel) -> {
            if (sel != null) {
                selectedId = sel.getId();
                txtNombre.setText(sel.getNombreCompleto());
                txtEdad.setText(String.valueOf(sel.getEdad()));
                txtCurp.setText(sel.getCurp());
                txtDomicilio.setText(sel.getDomicilio());
                cbTipoPension.setValue(sel.getTipoPension());
                cbEstadoSalud.setValue(sel.getEstadoSalud());
            }
        });
    }

    private void cargarTabla() {
        tblPersonas.setItems(FXCollections.observableArrayList(service.findAll()));
    }

    @FXML
    private void onGuardar() {
        try {
            if (!validarCampos()) return;

            Persona p = new Persona(
                null,
                txtNombre.getText().trim(),
                Integer.parseInt(txtEdad.getText().trim()),
                txtCurp.getText().trim(),
                txtDomicilio.getText().trim(),
                cbTipoPension.getValue(),
                cbEstadoSalud.getValue()
            );
            service.save(p);
            cargarTabla();
            limpiar();
            mostrarMensaje("Persona registrada correctamente.");
        } catch (NumberFormatException e) {
            mostrarMensaje("Error: La edad debe ser un número válido.");
        }
    }

    @FXML
    private void onActualizar() {
        if (selectedId == null) {
            mostrarMensaje("Seleccione una persona de la tabla.");
            return;
        }
        try {
            if (!validarCampos()) return;

            Persona p = new Persona(
                selectedId,
                txtNombre.getText().trim(),
                Integer.parseInt(txtEdad.getText().trim()),
                txtCurp.getText().trim(),
                txtDomicilio.getText().trim(),
                cbTipoPension.getValue(),
                cbEstadoSalud.getValue()
            );
            service.update(p);
            cargarTabla();
            limpiar();
            mostrarMensaje("Registro actualizado.");
        } catch (NumberFormatException e) {
            mostrarMensaje("Error: La edad debe ser un número válido.");
        }
    }

    @FXML
    private void onEliminar() {
        if (selectedId == null) {
            mostrarMensaje("Seleccione una persona de la tabla.");
            return;
        }
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,
                "¿Eliminar el registro seleccionado?", ButtonType.OK, ButtonType.CANCEL);
        confirm.showAndWait().ifPresent(r -> {
            if (r == ButtonType.OK) {
                service.delete(selectedId);
                cargarTabla();
                limpiar();
                mostrarMensaje("Registro eliminado.");
            }
        });
    }

    @FXML
    private void onFiltrarEdad() {
        try {
            int min = Integer.parseInt(txtEdadMin.getText().trim());
            int max = Integer.parseInt(txtEdadMax.getText().trim());
            tblPersonas.setItems(FXCollections.observableArrayList(service.filtrarPorEdad(min, max)));
            mostrarMensaje("Filtrado por edad: " + min + " - " + max);
        } catch (NumberFormatException e) {
            mostrarMensaje("Ingrese valores numéricos válidos para edad.");
        }
    }

    @FXML
    private void onFiltrarPension() {
        TipoPension tipo = cbFiltroTipoPension.getValue();
        if (tipo == null) {
            mostrarMensaje("Seleccione un tipo de pensión.");
            return;
        }
        tblPersonas.setItems(FXCollections.observableArrayList(service.filtrarPorPension(tipo)));
        mostrarMensaje("Filtrado por pensión: " + tipo);
    }

    @FXML
    private void onFiltrarAvanzado() {
        try {
            int min = txtEdadMin.getText().isBlank() ? 0 : Integer.parseInt(txtEdadMin.getText().trim());
            int max = txtEdadMax.getText().isBlank() ? 200 : Integer.parseInt(txtEdadMax.getText().trim());
            TipoPension tipo = cbFiltroTipoPension.getValue();
            tblPersonas.setItems(FXCollections.observableArrayList(service.filtrarAvanzado(min, max, tipo)));
            mostrarMensaje("Filtro avanzado aplicado.");
        } catch (NumberFormatException e) {
            mostrarMensaje("Error en los valores de filtro.");
        }
    }

    @FXML
    private void onLimpiarFiltros() {
        txtEdadMin.clear();
        txtEdadMax.clear();
        cbFiltroTipoPension.setValue(null);
        cargarTabla();
        mostrarMensaje("Filtros eliminados.");
    }

    private boolean validarCampos() {
        if (txtNombre.getText().isBlank() || txtEdad.getText().isBlank() ||
            txtCurp.getText().isBlank() || txtDomicilio.getText().isBlank() ||
            cbTipoPension.getValue() == null || cbEstadoSalud.getValue() == null) {
            mostrarMensaje("Todos los campos son obligatorios.");
            return false;
        }
        int edad;
        try {
            edad = Integer.parseInt(txtEdad.getText().trim());
        } catch (NumberFormatException e) {
            mostrarMensaje("La edad debe ser un número válido.");
            return false;
        }
        if (edad < 60) {
            mostrarMensaje("La edad debe ser mayor o igual a 60.");
            return false;
        }
        if (txtCurp.getText().trim().length() < 10) {
            mostrarMensaje("CURP inválido (mínimo 10 caracteres).");
            return false;
        }
        return true;
    }

    private void limpiar() {
        txtNombre.clear(); txtEdad.clear();
        txtCurp.clear(); txtDomicilio.clear();
        cbTipoPension.setValue(null); cbEstadoSalud.setValue(null);
        selectedId = null;
        tblPersonas.getSelectionModel().clearSelection();
    }

    private void mostrarMensaje(String msg) {
        lblMensaje.setText(msg);
    }
}
