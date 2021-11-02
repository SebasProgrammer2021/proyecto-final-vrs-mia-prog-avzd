package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.DetalleSubasta;
import co.edu.uniquindio.proyecto.entidades.Subasta;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.DetalleSubastaRepo;
import co.edu.uniquindio.proyecto.repositorios.SubastaRepo;
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
public class DetalleSubastaTest {

    @Autowired
    private DetalleSubastaRepo detalleSubastaRepo;

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private SubastaRepo subastaRepo;

    @Test
    @Sql("classpath:dbInserts.sql")
    public void registrarDetalleSubastaTest(){

        //Traigo el Usuario (2001) "Mario Fuentes"
        Usuario usuario = usuarioRepo.findById(2001).orElse(null);

        //Traigo la Subasta (202)
        Subasta subasta = subastaRepo.findById(202).orElse(null);

        //Creo una DetalleSubasta
        DetalleSubasta detalleSubasta = new DetalleSubasta(310, subasta, usuario, 185000, LocalDate.now() );
        //Guardo la DetalleSubasta
        DetalleSubasta detalleSubGuardado = detalleSubastaRepo.save(detalleSubasta);

        System.out.println(detalleSubGuardado);
        //Verificaión
        Assertions.assertNotNull(detalleSubGuardado);
    }

    //Actualizar DetalleSubasta
    @Test
    @Sql("classpath:dbInserts.sql")
    public void actualizarDetalleSubastaTest(){

        DetalleSubasta guardado = detalleSubastaRepo.findById(304).orElse(null);
        //modifico la DetalleSubasta
        guardado.setValor(2556000);

        //guardo la DetalleSubasta
        detalleSubastaRepo.save(guardado);

        DetalleSubasta detalleSubBuscado = detalleSubastaRepo.findById(304).orElse(null);
        //verifico lo modificado
        Assertions.assertEquals(2556000, detalleSubBuscado.getValor());
        System.out.println("--DetalleSubasta--: "+detalleSubBuscado);

    }

    //Eliminar DetalleSubasta
    @Test
    @Sql("classpath:dbInserts.sql")
    public void eliminarDetalleSubastaTest(){

        //Elimino la DetalleSubasta "303"
        detalleSubastaRepo.deleteById(303);

        //Busco la DetalleSubasta eliminado
        DetalleSubasta detalleSubBuscado = detalleSubastaRepo.findById(303).orElse(null);

        Assertions.assertNull(detalleSubBuscado);
        System.out.println("DetalleSubasta Eliminada");

    }

    //Listar Compra
    @Test
    @Sql("classpath:dbInserts.sql")
    public void listarDetalleSubastaTest(){
        //Listo las DetalleSubastas
        List<DetalleSubasta> detalleSubastas = detalleSubastaRepo.findAll();

        //Imprimir la lista de DetalleSubastas
        detalleSubastas.forEach(u -> System.out.println(u));
    }
}
