package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.dto.ProductoValido;
import co.edu.uniquindio.proyecto.dto.ProductosPorUsuario;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
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

    //    forma 1
    @Query("select p.nombre, p.descripcion, p.precio, p.ciudad.nombre from Producto p where  :fechaActual <  p.fecha_limite")
    List<Object[]> listaProductosValidos(LocalDateTime fechaActual);

    //    forma 2 con dto, no funciona
    //yano me devuelve un array de objetos, me devuelve un objeto, y es mas fácil manipularlo
    //    marca lo rojo como advertencia proque piensa que va a devolver una lista, pero no es un errro
//    @Query("select new co.edu.uniquindio.proyecto.dto.ProductoValido(p.nombre, p.descripcion, p.precio, p.ciudad.nombre, p.descuento) from Producto p where  :fechaActual <  p.fecha_limite")
//    List<ProductoValido> listaProductosValidos(LocalDateTime fechaActual);

    @Query("select c.nombre, count(p) from Producto p join p.categorias c group by c")
    List<Object[]> obtnTotalProductosPorCategoria();

    @Query("select p from Producto p where p.listaComentarios is empty ")
    List<Producto> obtnProductosSinComentarios();

//    forma 1
//    List<Producto> findByNombreContains(String nombre);

    //    forma 2
    @Query("select p from Producto p where p.nombre like concat('%',:nombre,'%')")
    List<Producto> findByNombreContains(String nombre);

    @Query("select new co.edu.uniquindio.proyecto.dto.ProductosPorUsuario(p.usuario.codigo, p.usuario.email, count(p)) from Producto p group by p.usuario")
    List<ProductosPorUsuario> obtnProductoEnVentaPorUsuario();

    @Query("select p, count(p) as total from Producto p join p.categorias c group by c order by total desc")
    List<Object[]> obtnCategoriaMasUsada();

    //queda pendinete la prueba unitaria
    @Query("select avg(lc.calificacion) from Producto p join p.listaComentarios lc where p.codigo = :codigo")
    List<Object[]> obtnCalificacioPromedioProducto();
}