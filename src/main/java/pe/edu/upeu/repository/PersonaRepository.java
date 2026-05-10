package pe.edu.upeu.repository;

import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import pe.edu.upeu.enums.TipoPension;
import pe.edu.upeu.model.Persona;

import java.util.List;

@JdbcRepository(dialect = Dialect.H2)
public interface PersonaRepository extends CrudRepository<Persona, Long> {

    List<Persona> findByEdadBetween(int min, int max);

    List<Persona> findByTipoPension(TipoPension tipoPension);

    List<Persona> findByEdadBetweenAndTipoPension(int min, int max, TipoPension tipoPension);
}
