package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

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
public interface ProductoRepo extends JpaRepository<Producto, Integer> {

    Page<Producto> findAll(Pageable paginador);

    @Query("select p.usuario.nombre from Producto p where p.codigo = :id")
    String obtenerNombreVendedor(Integer id);

    @Query("select c from Producto p join p.listaComentarios c where p.codigo = :id")
    List<ComentarioRepo> obtenerComentarios(Integer id);

    @Query("select c from Comentario c where c.producto.codigo = :id")
    List<ComentarioRepo> obtenerComentarios2(Integer id);

    @Query("select p.nombre, lc from Producto p left join p.listaComentarios lc")
    List<Object[]> listaProdutosAndComentario();

    //    distinct para que no repita un usuario
    @Query("select distinct lc.usuario from Producto p join p.listaComentarios lc where p.codigo = :codigo")
    List<Usuario> listarUsuariosEnComentarios(Integer codigo);
}
