package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyectos.NegocioApplication;
import co.edu.uniquindio.proyectos.servicios.UsuarioServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

//con esto indicamos que clase vamos aprobar
@SpringBootTest(classes = NegocioApplication.class)

//para que las pruebas que nosotros ejecutemos aqui no afecten la info de la bd
@Transactional
// NO FUNCIONA NINGUNO POR PORBLEMAS DEL REPO DE USUARIO QUE LLAMA AL METODO ---REGISTRAR-USUARIO

public class UsuarioServicioTest {
    //cargar la clase del servicio
    @Autowired
    private UsuarioServicio usuarioServicio;

    @Test
    public void registrarTest() {
        Usuario usuario = new Usuario(12927, "Angela", "angela@gmail.com", "987asdf_", "soyAngela", "Comprador", null, null);
        try {
            Usuario usuarioRespuesta = usuarioServicio.registrarUsuario(usuario);
            Assertions.assertNotNull(usuarioRespuesta);
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.assertTrue(false);
        }
    }
    //probar lo que hicimos ne le servicio

    @Test
    public void eliminarTest() {
        try {
            usuarioServicio.eliminarUsuario(2002);
            Assertions.assertTrue(true
            );
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.assertTrue(false);

        }
    }

    @Test
    public void listarTest() {
        List<Usuario> lista = usuarioServicio.listarUsuario();
        lista.forEach(System.out::println);
    }

    @Test
    public void actualizarTest() {
        try {
            Usuario u = usuarioServicio.obtnUsuario(2002);
            u.setPassword("lñaskjdfo2394u0293");
            usuarioServicio.actualizarUsuario(u);
            Usuario usuarioModificado = usuarioServicio.obtnUsuario(2002);
            Assertions.assertEquals("lñaskjdfo2394u0293", usuarioModificado.getPassword());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
