package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Subasta;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import co.edu.uniquindio.proyecto.repositorios.SubastaRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SubastaTest {

    @Autowired
    private SubastaRepo subastaRepo;

    @Autowired
    private ProductoRepo productoRepo;

    //Registrar Subasta
    @Test
    @Sql("classpath:dbInserts.sql")
    public void registrarSubastaTest(){

        //Traigo el producto (2) "ASADOR CARBON TIPO BARRIL CHAR-BROIL"
        Producto producto = productoRepo.findById(2).orElse(null);

        //Creo una Subasta
        Subasta subasta = new Subasta(208, LocalDateTime.of(2022,04,25,23,59), producto);
        //Guardo la Subasta
        Subasta subastaGuardado = subastaRepo.save(subasta);

        System.out.println(subastaGuardado);
        //Verificaión
        Assertions.assertNotNull(subastaGuardado);
    }

    //Actualizar Subasta
    @Test
    @Sql("classpath:dbInserts.sql")
    public void actualizarSubastaTest(){

        Subasta guardado = subastaRepo.findById(203).orElse(null);
        //modifico la Subasta
        guardado.setFecha_limite(LocalDateTime.of(2023,01,2,23,59));

        //guardo la Subasta
        subastaRepo.save(guardado);

        Subasta subastaBuscado = subastaRepo.findById(203).orElse(null);
        //verifico lo modificado
        Assertions.assertEquals(LocalDateTime.of(2023,01,2,23,59), subastaBuscado.getFecha_limite());
        System.out.println("--Subasta--: "+subastaBuscado);

    }

    //Eliminar Subasta
    @Test
    @Sql("classpath:dbInserts.sql")
    public void eliminarSubastaTest(){

        //Elimino la Subasta "204"
        subastaRepo.deleteById(204);

        //Busco la Subasta eliminado
        Subasta subastaBuscado = subastaRepo.findById(204).orElse(null);

        Assertions.assertNull(subastaBuscado);
        System.out.println("Subasta Eliminada");

    }

    //Listar Subasta
    @Test
    @Sql("classpath:dbInserts.sql")
    public void listarSubastaTest(){
        //Listo las Subastas
        List<Subasta> subastas = subastaRepo.findAll();

        //Imprimir la lista de Subastas
        subastas.forEach(u -> System.out.println(u));
    }
}
