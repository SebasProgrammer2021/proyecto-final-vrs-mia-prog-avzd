package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;

import java.util.*;
import java.util.stream.Collectors;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsuarioTest {

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private CiudadRepo ciudadRepo;

    //Registrar Usuario
    @Test
    @Sql("classpath:dbInserts.sql")
    public void registrarUsuarioTest() {

        //Traigo la ciudad (2) "Pereira"
        Ciudad ciudad = ciudadRepo.findById(2).orElse(null);

        //Creo Telefonos
        Map<String, String> telefonos = new HashMap<>();
        telefonos.put("Personal", "3128190456");
        telefonos.put("Trabajo", "7579863");

        //Creo un Usuario
        Usuario usuario = new Usuario(2010, "Mariana Correa", "mariana@email.com", "B02020", "Vendedor", ciudad, telefonos);
        //Guardo el Usuario
        Usuario usuarioGuardado = usuarioRepo.save(usuario);

        System.out.println(usuarioGuardado);
        //Verificaión
        Assertions.assertNotNull(usuarioGuardado);
    }

    //Actualizar Usuario
    @Test
    @Sql("classpath:dbInserts.sql")
    public void actualizarUsuarioTest() {

        Usuario guardado = usuarioRepo.findById(2003).orElse(null);
        //modifico el Usuario
        guardado.setPassword("nuevo123");

        //guardo el Usuario
        usuarioRepo.save(guardado);

        Usuario usuarioBuscado = usuarioRepo.findById(2003).orElse(null);
        //verifico lo modificado
        Assertions.assertEquals("nuevo123", usuarioBuscado.getPassword());
        System.out.println("--Usuario--: " + usuarioBuscado);

    }

    //Eliminar Usuario
    @Test
    @Sql("classpath:dbInserts.sql")
    public void eliminarUsuarioTest() {

        //Elimino el Usuario "2004"
        usuarioRepo.deleteById(2004);

        //Busco el Usuario eliminado
        Usuario usuarioBuscado = usuarioRepo.findById(2004).orElse(null);

        Assertions.assertNull(usuarioBuscado);
        System.out.println("Usuario Eliminado");

    }

    //Listar Usuario
    @Test
    @Sql("classpath:dbInserts.sql")
    public void listarUsuarioTest() {
        //Listo los Usuarios
        List<Usuario> usuarios = usuarioRepo.findAll();

        //Imprimir la lista de Usuarios
        usuarios.forEach(u -> System.out.println(u));
    }

    @Test
    @Sql("classpath:dbInserts.sql")
    public void filtrarNombreTest() {
        List<Usuario> lista = usuarioRepo.findAllByNombreContains("Maria");
        //forma 1
        lista.forEach(u -> System.out.println(u));
        //forma 2
        lista.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dbInserts.sql")
    public void filtrarEmailTest() {
        Optional<Usuario> usuario = usuarioRepo.findByEmail("sol@emnail.com");

        if (usuario.isPresent()) {
            System.out.println(usuario.get());
        } else {
            System.out.println("NO_EXISTE_CORREO");
        }
    }

    @Test
    @Sql("classpath:dbInserts.sql")
    public void paginarListaTest() {
        Pageable paginador = PageRequest.of(0, 2);
        Page<Usuario> lista = usuarioRepo.findAll(paginador);

        System.out.println(lista.stream().collect(Collectors.toList()));

    }

    @Test
    @Sql("classpath:dbInserts.sql")
    public void ordenarListaTest() {
        List<Usuario> lista = usuarioRepo.findAll(Sort.by("nombre"));

        System.out.println(lista);

    }
}
