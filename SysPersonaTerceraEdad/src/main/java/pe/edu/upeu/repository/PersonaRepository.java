package pe.edu.upeu.repository;

import pe.edu.upeu.Config.DatabaseConnection;
import pe.edu.upeu.Config.DatabaseConnection;
import pe.edu.upeu.enums.EstadoSalud;
import pe.edu.upeu.enums.TipoPension;
import pe.edu.upeu.model.Persona;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Esta clase ahora trabaja con base de datos SQLite
public class PersonaRepository {

    // LISTAR TODOS
    public List<Persona> findAll() {
        List<Persona> lista = new ArrayList<>();

        String sql = "SELECT * FROM persona";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Persona p = new Persona(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getInt("edad"),
                        rs.getString("curp"),
                        rs.getString("domicilio"),
                        TipoPension.valueOf(rs.getString("tipoPension")),
                        EstadoSalud.valueOf(rs.getString("estadoSalud"))
                );
                lista.add(p);
            }

        } catch (Exception e) {
            System.out.println("Error al listar: " + e.getMessage());
        }

        return lista;
    }

    // GUARDAR
    public void save(Persona p) {
        String sql = "INSERT INTO persona(nombre, edad, curp, domicilio, tipoPension, estadoSalud) VALUES(?,?,?,?,?,?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.getNombreCompleto());
            stmt.setInt(2, p.getEdad());
            stmt.setString(3, p.getCurp());
            stmt.setString(4, p.getDomicilio());
            stmt.setString(5, p.getTipoPension().name());
            stmt.setString(6, p.getEstadoSalud().name());

            stmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }

    // ACTUALIZAR
    public void update(Persona p) {
        String sql = "UPDATE persona SET nombre=?, edad=?, curp=?, domicilio=?, tipoPension=?, estadoSalud=? WHERE id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.getNombreCompleto());
            stmt.setInt(2, p.getEdad());
            stmt.setString(3, p.getCurp());
            stmt.setString(4, p.getDomicilio());
            stmt.setString(5, p.getTipoPension().name());
            stmt.setString(6, p.getEstadoSalud().name());
            stmt.setInt(7, p.getId());

            stmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error al actualizar: " + e.getMessage());
        }
    }

    // ELIMINAR
    public void delete(int id) {
        String sql = "DELETE FROM persona WHERE id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error al eliminar: " + e.getMessage());
        }
    }

    // FILTRAR POR EDAD
    public List<Persona> findByRangoEdad(int min, int max) {
        List<Persona> lista = new ArrayList<>();

        String sql = "SELECT * FROM persona WHERE edad BETWEEN ? AND ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, min);
            stmt.setInt(2, max);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Persona p = new Persona(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getInt("edad"),
                        rs.getString("curp"),
                        rs.getString("domicilio"),
                        TipoPension.valueOf(rs.getString("tipoPension")),
                        EstadoSalud.valueOf(rs.getString("estadoSalud"))
                );
                lista.add(p);
            }

        } catch (Exception e) {
            System.out.println("Error en filtro edad: " + e.getMessage());
        }

        return lista;
    }

    // FILTRAR POR PENSIÓN
    public List<Persona> findByTipoPension(TipoPension tipo) {
        List<Persona> lista = new ArrayList<>();

        String sql = "SELECT * FROM persona WHERE tipoPension=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tipo.name());

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Persona p = new Persona(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getInt("edad"),
                        rs.getString("curp"),
                        rs.getString("domicilio"),
                        TipoPension.valueOf(rs.getString("tipoPension")),
                        EstadoSalud.valueOf(rs.getString("estadoSalud"))
                );
                lista.add(p);
            }

        } catch (Exception e) {
            System.out.println("Error en filtro pensión: " + e.getMessage());
        }

        return lista;
    }
}