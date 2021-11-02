package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.CompraRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class Compratest {

    @Autowired
    private CompraRepo compraRepo;

    @Autowired
    private UsuarioRepo usuarioRepo;

    //Registrar Compra
    @Test
    @Sql("classpath:dbInserts.sql")
    public void registrarCompraTest(){

        //Traigo el Usuario (2001) "Mario Fuentes"
        Usuario usuario = usuarioRepo.findById(2001).orElse(null);

        //Creo una Compra
        Compra compra = new Compra(110, LocalDate.now(), "Efectivo", usuario);
        //Guardo la Compra
        Compra compraGuardado = compraRepo.save(compra);

        System.out.println(compraGuardado);
        //Verificaión
        Assertions.assertNotNull(compraGuardado);
    }

    //Actualizar Compra
    @Test
    @Sql("classpath:dbInserts.sql")
    public void actualizarCompraTest(){

        Compra guardado = compraRepo.findById(102).orElse(null);
        //modifico la Compra
        guardado.setMedioPago("Targeta de Credito");

        //guardo la Compra
        compraRepo.save(guardado);

        Compra compraBuscado = compraRepo.findById(102).orElse(null);
        //verifico lo modificado
        Assertions.assertEquals("Targeta de Credito", compraBuscado.getMedioPago());
        System.out.println("--Compra--: "+compraBuscado);

    }

    //Eliminar Compra
    @Test
    @Sql("classpath:dbInserts.sql")
    public void eliminarCompraTest(){

        //Elimino la Compra "103"
        compraRepo.deleteById(103);

        //Busco la Compra eliminado
        Compra compraBuscado = compraRepo.findById(103).orElse(null);

        Assertions.assertNull(compraBuscado);
        System.out.println("Compra Eliminada");

    }

    //Listar Compra
    @Test
    @Sql("classpath:dbInserts.sql")
    public void listarCompraTest(){
        //Listo los Compras
        List<Compra> compras = compraRepo.findAll();

        //Imprimir la lista de Compras
        compras.forEach(u -> System.out.println(u));
    }
}
