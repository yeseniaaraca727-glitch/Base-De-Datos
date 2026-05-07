package pe.edu.upeu.service;

import pe.edu.upeu.enums.TipoPension;
import pe.edu.upeu.model.Persona;
import pe.edu.upeu.repository.PersonaRepository;
import java.util.List;

public class PersonaServiceImpl implements PersonaService {

    private final PersonaRepository repo = new PersonaRepository();

    @Override
    public List<Persona> listarTodos() {
        return repo.findAll();
    }

    @Override
    public void guardar(Persona p) {
        repo.save(p);
    }

    @Override
    public void actualizar(Persona p) {
        repo.update(p);
    }

    @Override
    public void eliminar(int id) {
        repo.delete(id);
    }

    @Override
    public List<Persona> filtrarPorEdad(int min, int max) {
        return repo.findByRangoEdad(min, max);
    }

    @Override
    public List<Persona> filtrarPorPension(TipoPension tipo) {
        return repo.findByTipoPension(tipo);
    }
}