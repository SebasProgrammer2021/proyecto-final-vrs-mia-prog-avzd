package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * En la clase Chat se pretende generar la relacion entre un usuario y un vendedor para que pueda haber una comunicacion entre estos por
 * medio del producto
 * Integer codigo
 * Usuario usuario_comprador
 * Producto codigo_producto
 * List<Mensaje> listaMansajes
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
public class Chat implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario usuario_comprador;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Producto codigo_producto;

    @OneToMany(mappedBy = "chat")
    @ToString.Exclude
    private List<Mensaje> listaMansajes;

    /**
     * Metodo constructor con argumentos
     * @param codigo
     * @param usuario_comprador
     * @param codigo_producto
     */
    public Chat(Integer codigo, Usuario usuario_comprador, Producto codigo_producto) {
        this.codigo = codigo;
        this.usuario_comprador = usuario_comprador;
        this.codigo_producto = codigo_producto;
    }
}
