package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.dto.ProductosPorUsuario;
import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.entidades.Producto;
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
public interface CompraRepo extends JpaRepository<Compra, Integer> {

    @Query("select count(distinct ldc.producto) from Compra c join c.listaDetallesCompra ldc where c.usuario.codigo = :codigo")
    Long obtnListaProductosComprados(Integer codigo);

    @Query("select sum(dc.precioProducto * dc.unidades) from DetalleCompra dc where dc.producto.usuario.codigo = :codigo")
    List calcularTotalVentasXUsuario(Integer codigo);

//    total que ha gastado un user en compras
    @Query("select sum(dc.precioProducto * dc.unidades) from Compra c join c.listaDetallesCompra dc where c.usuario.codigo = :codigo")
    List calcularTotalComprasXUsuario(Integer codigo);

    //falta hacer el dto, cambiar el obj por dto, que contenga la comrpa y el objeto detalle
    @Query("select c, dc from Compra c join c.listaDetallesCompra dc where c.usuario.codigo = :codigo")
    List<Object[]> obtnComprasUsuario(Integer codigo);
}
