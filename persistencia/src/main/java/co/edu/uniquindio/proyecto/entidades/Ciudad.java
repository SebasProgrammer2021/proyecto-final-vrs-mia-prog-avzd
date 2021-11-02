package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * En la clase Ciudad se relacionaran los usuarios y los productos que pertenecen a dicha ciudad
 * Integer codigo
 * String nombre
 * List<Usuario> listaUsuarios
 * List<Producto> listaProductos
 *
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
public class Ciudad implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(nullable = false, length = 100)
    private String nombre;

    @OneToMany(mappedBy = "ciudad")
    @ToString.Exclude
    private List<Usuario> listaUsuarios;

    @OneToMany(mappedBy = "ciudad")
    @ToString.Exclude
    private List<Producto> listaProductos;

    /**
     * Metodo constructor con argumentos
     * @param codigo
     * @param nombre
     */
    public Ciudad(Integer codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }
}
