package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * En la clase DetalleSubasta se pretende gestionar la informacion del producto que se etsa subastando y el usuario que esta
 * ofreciendo
 * Integer codigo
 * Subasta subasta
 * Usuario usuario
 * Integer valor
 * LocalDate fechaSubasta
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
public class DetalleSubasta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @ManyToOne
    private Subasta subasta;

    @ManyToOne
    private Usuario usuario;

    @Column(nullable = false)
    @Positive
    private Integer valor;

    @Column(nullable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDate fechaSubasta;

    /**
     * Metodo constructor con argumentos
     * @param codigo
     * @param subasta
     * @param usuario
     * @param valor
     * @param fechaSubasta
     */
    public DetalleSubasta(Integer codigo, Subasta subasta, Usuario usuario, Integer valor, LocalDate fechaSubasta) {
        this.codigo = codigo;
        this.subasta = subasta;
        this.usuario = usuario;
        this.valor = valor;
        this.fechaSubasta = fechaSubasta;
    }
}
