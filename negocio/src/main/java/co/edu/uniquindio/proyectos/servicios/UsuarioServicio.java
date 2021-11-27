package co.edu.uniquindio.proyectos.servicios;

import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;

import java.util.List;

//la interfaz es uan clase abstracta
public interface UsuarioServicio {

    //no es correcto esperar a que la bd nos arroje error

    Usuario registrarUsuario(Usuario u) throws Exception;

    Usuario actualizarUsuario(Usuario u) throws Exception;

    void eliminarUsuario(Integer codigo) throws Exception;

    List<Usuario> listarUsuario();

    List<Producto> listaFavoritos(String email) throws Exception;

    Usuario obtnUsuario(Integer codigo) throws Exception;


}
