package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * En esta interface se realiza la relacion con la clase que se pretende realizar las pruebas, extiende de JpaRepository
 * para utilizar los metodos que esta nos facilita como el save.
 * <p>
 * Integrantes:
 * Juan Sebastian Tobon Alcaraz
 * Sebastian Londoño
 * Rodrigo Acosta Restrepo
 */

@Repository
public interface UsuarioRepo extends JpaRepository<Usuario, Integer> {

    //    FORMA 1
//    @Query("select u from Usuario where u.nombre = :nombre")
//    List<Usuario> obtenerUsuarioPorNombre(String nombre);

    //    FORMA 2
//    List<Usuario> findAllByNombre(String nombre);

    //    me devolveria uan lista de usuarios que contengan este texto, el nombre
    List<Usuario> findAllByNombreContains(String nombre);

    //    me devuelve un user que contenga este correo. solo uno proque el email es unique
    Optional<Usuario> findByEmail(String email);

    //  @Query("select u from Usuario u where u.email = :email and u.password = :clave")
    //  Optional<Usuario> verificarAutenticacion(String email, String clave);

    Optional<Usuario> findByEmailAndPassword(String email, String clave);

    Page<Usuario> findAll(Pageable paginador);

    //    la lista del in, va entre parentesis
    //    el in y el join solo funcionan cuando yo quiero mezclar un obj con una lista de cosas.
    @Query("select p from Usuario u, IN (u.usuarioListProductosFav) p where u.email = :email")
    List<Producto> obtenerProductoFavoritos(String email);

//    la misma consulta con left join me trae los que son nulos tambien
    @Query("select u.email, u.nombre, p from Usuario u left join u.listaProductos p")
    List<Object[]> listarUsuariosProductosPublicados();


}
