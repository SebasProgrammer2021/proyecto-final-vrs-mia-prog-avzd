package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.entidades.DetalleCompra;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.repositorios.CompraRepo;
import co.edu.uniquindio.proyecto.repositorios.DetalleCompraRepo;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DetalleCompraTest {

    @Autowired
    private DetalleCompraRepo detalleCompraRepo;

    @Autowired
    private CompraRepo compraRepo;

    @Autowired
    private ProductoRepo productoRepo;

    //Registrar DetalleCompra
    @Test
    @Sql("classpath:dbInserts.sql")
    public void registrarDetalleCompraTest(){

        //Traigo la Compra (102)
        Compra compra = compraRepo.findById(102).orElse(null);

        //Traigo el Producto (3)
        Producto producto = productoRepo.findById(3).orElse(null);

        //Creo un DetalleCompra
        DetalleCompra detalleCompra = new DetalleCompra(410, 30, 50.000, producto, compra);
        //Guardo el DetalleCompra
        DetalleCompra detalleComGuardado = detalleCompraRepo.save(detalleCompra);

        System.out.println(detalleComGuardado);
        //Verificaión
        Assertions.assertNotNull(detalleComGuardado);
    }

    //Actualizar DetalleCompra
    @Test
    @Sql("classpath:dbInserts.sql")
    public void actualizarDetalleCompraTest(){

        DetalleCompra guardado = detalleCompraRepo.findById(102).orElse(null);
        //modifico la DetalleCompra
        guardado.setUnidades(50);

        //guardo la DetalleCompra
        detalleCompraRepo.save(guardado);

        DetalleCompra detalleComBuscado = detalleCompraRepo.findById(102).orElse(null);
        //verifico lo modificado
        Assertions.assertEquals(50, detalleComBuscado.getUnidades());
        System.out.println("--DetalleCompra--: "+detalleComBuscado);

    }

    //Eliminar DetalleCompra
    @Test
    @Sql("classpath:dbInserts.sql")
    public void eliminarDetalleCompraTest(){

        //Elimino la DetalleCompra "103"
        detalleCompraRepo.deleteById(103);

        //Busco la Compra eliminado
        DetalleCompra detalleComBuscado = detalleCompraRepo.findById(103).orElse(null);

        Assertions.assertNull(detalleComBuscado);
        System.out.println("DetalleCompra Eliminada");

    }

    //Listar DetalleCompra
    @Test
    @Sql("classpath:dbInserts.sql")
    public void listarDetalleCompraTest(){
        //Listo los DetalleCompras
        List<DetalleCompra> detalleCompras = detalleCompraRepo.findAll();

        //Imprimir la lista de DetalleCompra
        detalleCompras.forEach(u -> System.out.println(u));
    }
}
