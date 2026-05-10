package pe.edu.upeu.model;

import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.MappedProperty;
import io.micronaut.data.model.DataType;
import lombok.*;
import pe.edu.upeu.enums.EstadoSalud;
import pe.edu.upeu.enums.TipoPension;

@MappedEntity(value = "persona")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Persona {

    @Id
    @GeneratedValue(GeneratedValue.Type.AUTO)
    private Long id;

    @MappedProperty(type = DataType.STRING, value = "nombre")
    private String nombreCompleto;

    @MappedProperty(type = DataType.INTEGER, value = "edad")
    private Integer edad;

    @MappedProperty(type = DataType.STRING, value = "curp")
    private String curp;

    @MappedProperty(type = DataType.STRING, value = "domicilio")
    private String domicilio;

    @MappedProperty(type = DataType.STRING, value = "tipo_pension")
    private TipoPension tipoPension;

    @MappedProperty(type = DataType.STRING, value = "estado_salud")
    private EstadoSalud estadoSalud;
}
