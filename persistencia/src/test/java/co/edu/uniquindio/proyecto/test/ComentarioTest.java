package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.ComentarioRepo;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
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
public class ComentarioTest {

    @Autowired
    private ComentarioRepo comentarioRepo;

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private ProductoRepo productoRepo;

    //Registrar Comentario
    @Test
    @Sql("classpath:dbInserts.sql")
    public void registrarComentarioTest(){

        //Traigo el Usuario (2001) "Mario Fuentes"
        Usuario usuario = usuarioRepo.findById(2001).orElse(null);

        //Traigo el producto (2) "ASADOR CARBON TIPO BARRIL CHAR-BROIL"
       Producto producto = productoRepo.findById(2).orElse(null);

        //Creo un Comentario
        Comentario comentario = new Comentario(111, "Hola Papá estoy en Youtube","Ok Hijo", LocalDateTime.now(), 4.5f, producto, usuario );
        //Guardo el Comentario
        Comentario comentGuardado = comentarioRepo.save(comentario);

        System.out.println(comentGuardado);
        //Verificaión
        Assertions.assertNotNull(comentGuardado);
    }

    //Actualizar Comentario
    @Test
    @Sql("classpath:dbInserts.sql")
    public void actualizarComentarioTest(){

        Comentario guardado = comentarioRepo.findById(100).orElse(null);
        //modifico la calificacion
        guardado.setCalificacion(4.2f);

        //guardo el Comentario
        comentarioRepo.save(guardado);

        Comentario comentBuscado = comentarioRepo.findById(100).orElse(null);
        //verifico lo modificado
        Assertions.assertEquals(4.2f, comentBuscado.getCalificacion());
        System.out.println("--Comentario--: "+comentBuscado);

    }

    //Eliminar Comentario
    @Test
    @Sql("classpath:dbInserts.sql")
    public void eliminarComentarioTest(){

        //Elimino el Comentario "101"
        comentarioRepo.deleteById(101);

        Comentario comentBuscado = comentarioRepo.findById(101).orElse(null);

        Assertions.assertNull(comentBuscado);
        System.out.println("Comentario Eliminado");

    }

    //Listar Comentarios
    @Test
    @Sql("classpath:dbInserts.sql")
    public void listarComentarioTest(){
        //Listo los Comentarios
        List<Comentario> comentarios = comentarioRepo.findAll();

        //Imprimir la lista de Comentarios
        comentarios.forEach(u -> System.out.println(u));
    }

}
