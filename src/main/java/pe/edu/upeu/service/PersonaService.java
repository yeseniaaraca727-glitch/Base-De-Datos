package pe.edu.upeu.service;

import pe.edu.upeu.enums.TipoPension;
import pe.edu.upeu.model.Persona;

import java.util.List;
import java.util.Optional;

public interface PersonaService {
    Persona save(Persona p);
    Persona update(Persona p);
    void delete(Long id);
    List<Persona> findAll();
    Optional<Persona> findById(Long id);
    List<Persona> filtrarPorEdad(int min, int max);
    List<Persona> filtrarPorPension(TipoPension tipo);
    List<Persona> filtrarAvanzado(int min, int max, TipoPension tipo);
}
