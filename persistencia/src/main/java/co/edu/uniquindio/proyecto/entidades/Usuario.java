package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * En la clase Usuario se gestionara el objeto segun su rol, usuario o vendedor
 * Integer codigo
 * String nombre
 * String email
 * String password
 * String rol
 * Ciudad ciudad
 * Map<String, String> numTelefono
 * List<Chat> listaChats
 * List<Comentario> listaComentarios
 * List<Compra> listaCompras
 * List<Producto> listaProductos
 *
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
@ToString(callSuper = true)
public class Usuario extends Persona implements Serializable {

    @Column(nullable = false, length = 20)
    private String rol;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Ciudad ciudad;

    @OneToMany(mappedBy = "usuario_comprador")
    @ToString.Exclude
    private List<Chat> listaChats;

    @OneToMany(mappedBy = "usuario")
    @ToString.Exclude
    private List<Comentario> listaComentarios;

    @OneToMany(mappedBy = "usuario")
    @ToString.Exclude
    private List<Compra> listaCompras;

    @OneToMany(mappedBy = "usuario")
    @ToString.Exclude
    private List<Producto> listaProductos;

    @ManyToMany(mappedBy = "usuarios")
    private List<Producto> productos;

    @ElementCollection
    @Column(nullable = false)
    private Map<String, String> numTelefono;

    @ManyToMany
    private List<Producto> usuarioListProductosFav;

    @OneToMany(mappedBy = "usuario")
    @ToString.Exclude
    private List<DetalleSubasta> listaDetalleSubasta;


    /**
     * Metodo constructor con argumentos
     * @param codigo
     * @param nombre
     * @param email
     * @param password
     * @param rol
     * @param ciudad
     * @param numTelefono
     */
    public Usuario(Integer codigo, String nombre, String email, String password, String rol, Ciudad ciudad, Map<String, String> numTelefono) {
        super(codigo, nombre, email, password);
        this.rol = rol;
        this.ciudad = ciudad;
        this.numTelefono = numTelefono;
    }
}
