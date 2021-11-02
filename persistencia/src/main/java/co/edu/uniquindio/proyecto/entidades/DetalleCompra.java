package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.io.Serializable;

/**
 * En la clase DetalleCompra se prentende guardar los datos de la compra realizada por el usuario,
 * donde quedan los datos del producto y de la compra
 * Integer codigo
 * Integer unidades
 * Double precioProducto
 * Producto producto
 * Compra compra
 *
 * Integrantes:
 * Juan Sebastian Tobon Alcaraz
 * Sebastian Londoño
 * Rodrigo Acosta Restrepo
 */

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString
public class DetalleCompra implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(nullable = false)
    @Positive
    private Integer unidades;

    @Column(nullable = false)
    private Double precioProducto;

    @ManyToOne
    private Producto producto;

    @ManyToOne
    private Compra compra;

    /**
     * Metodo constructor con argumentos
     * @param codigo
     * @param unidades
     * @param precioProducto
     * @param producto
     * @param compra
     */
    public DetalleCompra(Integer codigo, Integer unidades, Double precioProducto, Producto producto, Compra compra) {
        this.codigo = codigo;
        this.unidades = unidades;
        this.precioProducto = precioProducto;
        this.producto = producto;
        this.compra = compra;
    }
}
