package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Future;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * En la clase Subasta se gestionara el producto que entrara en esta actividad.
 * Integer codigo
 * LocalDateTime fecha_limite
 * Producto producto
 * List<DetalleSubasta> detalleSubasta
 *
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
public class Subasta implements Serializable {

    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Id
    private Integer codigo;

    @Column(nullable = false)
    @Future
    private LocalDateTime fecha_limite;

    @ManyToOne
    private Producto producto;

    @OneToMany(mappedBy = "subasta")
    @ToString.Exclude
    private List<DetalleSubasta> detalleSubasta;

    /**
     * Metodo constructor con argumentos
     * @param codigo
     * @param fecha_limite
     * @param producto
     */
    public Subasta(Integer codigo, LocalDateTime fecha_limite, Producto producto) {
        this.codigo = codigo;
        this.fecha_limite = fecha_limite;
        this.producto = producto;
    }
}
