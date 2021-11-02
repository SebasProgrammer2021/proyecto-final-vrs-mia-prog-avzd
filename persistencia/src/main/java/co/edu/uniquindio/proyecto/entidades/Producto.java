package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * En la clase Producto se prentende gestionar todo lo relacionado con los productos
 * Integer codigo
 * String nombre
 * Integer unidades
 * String descripcion
 * Integer precio
 * LocalDateTime fecha_limite
 * Float descuento
 * Usuario usuario
 * Ciudad ciudad
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
public class Producto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(length = 100, nullable = false)
    private String nombre;

    @Column(nullable = false)
    @Positive
    private Integer unidades;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    @Positive
    private Integer precio;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Future
    private LocalDateTime fecha_limite;

    @Column(nullable = false)
    @Positive
    private Float descuento;

    @OneToMany(mappedBy = "producto")
    @ToString.Exclude
    private List<Comentario> listaComentarios;

    @OneToMany(mappedBy = "codigo_producto")
    @ToString.Exclude
    private List<Chat> listaChats;

    @ElementCollection
    @Column(nullable = false)
    private List<String> listaImagenes;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Ciudad ciudad;

    @OneToMany(mappedBy = "producto")
    @ToString.Exclude
    private List<Subasta> listaSubastas;

    @OneToMany(mappedBy = "producto")
    @ToString.Exclude
    private List<DetalleCompra> listaDetallesCompra;

    @ManyToMany(mappedBy = "productos")
    private List<Categoria> categorias;

    @ManyToMany
    private List<Usuario> usuarios;

    /**
     *Metodo constructor con argumentos
     * @param codigo
     * @param nombre
     * @param unidades
     * @param descripcion
     * @param precio
     * @param fecha_limite
     * @param descuento
     * @param usuario
     * @param ciudad
     */
    public Producto(Integer codigo, String nombre, Integer unidades, String descripcion, Integer precio, LocalDateTime fecha_limite, Float descuento, Usuario usuario, Ciudad ciudad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.unidades = unidades;
        this.descripcion = descripcion;
        this.precio = precio;
        this.fecha_limite = fecha_limite;
        this.descuento = descuento;
        this.usuario = usuario;
        this.ciudad = ciudad;
    }
}
