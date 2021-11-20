package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import lombok.*;

import javax.persistence.Entity;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class ProductoValido {

    private String nombre;
    private String descripcion;
    private Integer precio;
    private Ciudad ciudad;
    private Float descuento;

}
