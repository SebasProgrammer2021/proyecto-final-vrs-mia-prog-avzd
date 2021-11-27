package co.edu.uniquindio.proyectos.servicios;

import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicioImpl implements UsuarioServicio {

    private final UsuarioRepo usuarioRepo;

    //PENDIENTE REVISAR AUTOWIRED
    public UsuarioServicioImpl(UsuarioRepo usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }


    @Override
    public Usuario registrarUsuario(Usuario u) throws Exception {
        Optional<Usuario> usuario = usuarioRepo.findById(u.getCodigo());
        if (usuario.isPresent()) {
            throw new Exception("El codigo del usuario ya existe");
        }

        usuario = buscarPorEmail(u.getEmail());
        if (usuario.isPresent()) {
            throw new Exception("El email del usuario ya existe");
        }

        usuario = usuarioRepo.findByUsername(u.getUsername());
        if (usuario.isPresent()) {
            throw new Exception("El username del usuario ya existe");
        }

        return usuarioRepo.save(u);
    }

    @Override
    public Usuario actualizarUsuario(Usuario u) throws Exception {
        /**
         *   Optional<Usuario> usuario = buscarPorEmail(u.getEmail());
         *         if (usuario.isPresent()) {
         *             throw new Exception("El email del usuario ya existe");
         *         }
         *
         *         usuario = usuarioRepo.findByUsername(u.getUsername());
         *         if (usuario.isPresent()) {
         *             throw new Exception("El username del usuario ya existe");
         *         }
         */
        Optional<Usuario> usuario = usuarioRepo.findById(u.getCodigo());
        if (usuario.isEmpty()) {
            throw new Exception("El usuario no existe");
        }
        return usuarioRepo.save(u);
    }

    private Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepo.findByEmail(email);
    }

    @Override
    public void eliminarUsuario(Integer codigo) throws Exception {
        Optional<Usuario> usuario = usuarioRepo.findById(codigo);
        if (usuario.isEmpty()) {
            throw new Exception("El codigo del usuario no existe");
        }
        usuarioRepo.deleteById(codigo);
    }

    @Override
    public List<Usuario> listarUsuario() {
        return usuarioRepo.findAll();
    }

    @Override
    public List<Producto> listaFavoritos(String email) throws Exception {
        Optional<Usuario> usuario = buscarPorEmail(email);
        if (usuario.isEmpty()) {
            throw new Exception("El correo no existe");
        }
        return usuarioRepo.obtenerProductoFavoritos(email);
    }

    @Override
    public Usuario obtnUsuario(Integer codigo) throws Exception {
        Optional<Usuario> usuario = usuarioRepo.findById(codigo);
        if (usuario.isEmpty()) {
            throw new Exception("El codigo no existe");
        }
        return usuario.get();
    }
}
