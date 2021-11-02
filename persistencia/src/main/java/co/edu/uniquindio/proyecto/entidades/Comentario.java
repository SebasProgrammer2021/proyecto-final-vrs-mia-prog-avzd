package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * En la clase Comentario se pretende guardar los comentarios que los usuarios dejaran en el producto relacionado.
 * Integer codigo
 * String mensaje
 * String respuesta
 * LocalDateTime fechaComentario
 * Float calificacion
 * Producto producto
 * Usuario usuario
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
public class Comentario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(nullable = false)
    private String mensaje;

    @Column(nullable = false)
    private String respuesta;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fechaComentario;

    @Column(nullable = false)
    private Float calificacion;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Producto producto;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario usuario;

    /**
     * Metodo constructor con argumentos
     * @param codigo
     * @param mensaje
     * @param respuesta
     * @param fechaComentario
     * @param calificacion
     * @param producto
     * @param usuario
     */
    public Comentario(Integer codigo, String mensaje, String respuesta, LocalDateTime fechaComentario, Float calificacion, Producto producto, Usuario usuario) {
        this.codigo = codigo;
        this.mensaje = mensaje;
        this.respuesta = respuesta;
        this.fechaComentario = fechaComentario;
        this.calificacion = calificacion;
        this.producto = producto;
        this.usuario = usuario;
    }
}
