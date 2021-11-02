package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Chat;
import co.edu.uniquindio.proyecto.entidades.Mensaje;
import co.edu.uniquindio.proyecto.repositorios.ChatRepo;
import co.edu.uniquindio.proyecto.repositorios.MensajeRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MensajeTest {

    @Autowired
    private MensajeRepo mensajeRepo;

    @Autowired
    private ChatRepo chatRepo;

    //Registrar Mensaje
    @Test
    @Sql("classpath:dbInserts.sql")
    public void registrarMensajeTest(){

        //Traigo el chat (124)
        Chat chat = chatRepo.findById(124).orElse(null);

        //Creo una Mensaje
        Mensaje mensaje = new Mensaje(1001, "Hola Admin Gracias por los productos ofrecidos", "Raul Garcia", LocalDateTime.now(),chat);
        //Guardo la Mensaje
        Mensaje mensajeGuardado = mensajeRepo.save(mensaje);

        System.out.println(mensajeGuardado);
        //Verificaión
        Assertions.assertNotNull(mensajeGuardado);
    }

    //Actualizar Mensaje
    @Test
    @Sql("classpath:dbInserts.sql")
    public void actualizarMensajeTest(){

        Mensaje guardado = mensajeRepo.findById(1002).orElse(null);
        //modifico el Mensaje
        guardado.setMensaje("Me Agrada el producto que compre, muchas gracias");

        //guardo el Mensaje
        mensajeRepo.save(guardado);

        Mensaje mensajeBuscado = mensajeRepo.findById(1002).orElse(null);
        //verifico lo modificado
        Assertions.assertEquals("Me Agrada el producto que compre, muchas gracias", mensajeBuscado.getMensaje());
        System.out.println("--Mensaje--: "+mensajeBuscado);

    }

    //Eliminar Mensaje
    @Test
    @Sql("classpath:dbInserts.sql")
    public void eliminarMensajeTest(){

        //Elimino el Mensaje "1004"
        mensajeRepo.deleteById(1004);

        //Busco el Mensaje eliminado
        Mensaje mensajeBuscado = mensajeRepo.findById(1004).orElse(null);

        Assertions.assertNull(mensajeBuscado);
        System.out.println("Mensaje Eliminada");

    }

    //Listar Mensaje
    @Test
    @Sql("classpath:dbInserts.sql")
    public void listarMensajeTest(){
        //Listo los Mensajes
        List<Mensaje> mensajes = mensajeRepo.findAll();

        //Imprimir la lista de Mensajes
        mensajes.forEach(u -> System.out.println(u));
    }
}
