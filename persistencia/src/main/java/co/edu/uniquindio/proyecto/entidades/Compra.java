package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * En la clase Compra se pretende guardar los datos del usuario que realizara la compra
 * Integer codigo
 * LocalDate fechaCompra
 * String medioPago
 * Usuario usuario
 * List<DetalleCompra> listaDetallesCompra
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
public class Compra implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(nullable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDate fechaCompra;

    @Column(nullable = false)
    private String medioPago;

    @ManyToOne
    private Usuario usuario;

    @OneToMany(mappedBy = "compra")
    @ToString.Exclude
    private List<DetalleCompra> listaDetallesCompra;

    /**
     * Metodo constructor con argumentos
     * @param codigo
     * @param fechaCompra
     * @param medioPago
     * @param usuario
     */
    public Compra(Integer codigo, LocalDate fechaCompra, String medioPago, Usuario usuario) {
        this.codigo = codigo;
        this.fechaCompra = fechaCompra;
        this.medioPago = medioPago;
        this.usuario = usuario;
    }
}
