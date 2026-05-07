package pe.edu.upeu.service;

import pe.edu.upeu.enums.TipoPension;
import pe.edu.upeu.model.Persona;

import java.util.List;

public interface PersonaService {
    List<Persona> listarTodos();
     void guardar(Persona p);
     void actualizar(Persona p);
     void eliminar(int id);
      List<Persona> filtrarPorEdad(int min, int max);
      List<Persona> filtrarPorPension(TipoPension tipo);
}
