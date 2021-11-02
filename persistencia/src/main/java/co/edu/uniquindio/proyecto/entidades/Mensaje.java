package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * En la clase Mensaje se pretende gestionar los mensajes que se enviaran entre usuario y vendedor
 * Integer codigo
 * String mensaje
 * String emisor
 * LocalDateTime fecha
 * Chat chat
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
public class Mensaje implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;
 
    @Column(nullable = false)
    private String mensaje;

    @Column(nullable = false)
    private String emisor;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fecha;

    @ManyToOne
    private Chat chat;

    /**
     * Metodo constructor con argumentos
     * @param codigo
     * @param mensaje
     * @param emisor
     * @param fecha
     * @param chat
     */
    public Mensaje(Integer codigo, String mensaje, String emisor, LocalDateTime fecha, Chat chat) {
        this.codigo = codigo;
        this.mensaje = mensaje;
        this.emisor = emisor;
        this.fecha = fecha;
        this.chat = chat;
    }
}
