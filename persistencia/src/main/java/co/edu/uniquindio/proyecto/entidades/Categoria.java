package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * En la clase categoria se pretende guardar unas categorias las cuales seran relacionadas con producto
 * Integer codigo
 * String nombre
 * List<Producto> productos
 * Integrantes:
 * Juan Sebastian Tobon Alcaraz
 * Sebastian Londoño
 * Rodrigo Acosta Restrepo
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Categoria implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(nullable = false, length = 100)
    private String nombre;

    @ManyToMany
    private List<Producto> productos;

    /**
     * Metodo constructor con argumentos
     * @param codigo
     * @param nombre
     */
    public Categoria(Integer codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }
}
