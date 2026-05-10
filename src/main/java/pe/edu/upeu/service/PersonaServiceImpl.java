package pe.edu.upeu.service;

import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;
import pe.edu.upeu.enums.TipoPension;
import pe.edu.upeu.model.Persona;
import pe.edu.upeu.repository.PersonaRepository;

import java.util.List;
import java.util.Optional;

@Transactional
@Singleton
public class PersonaServiceImpl implements PersonaService {

    private final PersonaRepository repo;

    public PersonaServiceImpl(PersonaRepository repo) {
        this.repo = repo;
    }

    @Override
    public Persona save(Persona p) {
        return repo.save(p);
    }

    @Override
    public Persona update(Persona p) {
        return repo.update(p);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Override
    public List<Persona> findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<Persona> findById(Long id) {
        return repo.findById(id);
    }

    @Override
    public List<Persona> filtrarPorEdad(int min, int max) {
        return repo.findByEdadBetween(min, max);
    }

    @Override
    public List<Persona> filtrarPorPension(TipoPension tipo) {
        return repo.findByTipoPension(tipo);
    }

    @Override
    public List<Persona> filtrarAvanzado(int min, int max, TipoPension tipo) {
        if (tipo == null) return repo.findByEdadBetween(min, max);
        return repo.findByEdadBetweenAndTipoPension(min, max, tipo);
    }
}
