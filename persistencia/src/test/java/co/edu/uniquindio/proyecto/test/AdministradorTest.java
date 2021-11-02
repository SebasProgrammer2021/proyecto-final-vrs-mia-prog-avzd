package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Administrador;
import co.edu.uniquindio.proyecto.repositorios.AdministradorRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AdministradorTest {

    @Autowired
    private AdministradorRepo administradorRepo;

    //Registrar Administrador
    @Test
    @Sql("classpath:dbInserts.sql")
    public void registrarAdministradorTest(){
        //Creo un Administrador
        Administrador administrador = new Administrador(1009,"Carlos2", "carlos2@email.com", "A12345");
        //Guardo el Administrador
        Administrador administradorGuardado = administradorRepo.save(administrador);

        System.out.println(administradorGuardado);
        //Verificaion
        Assertions.assertNotNull(administradorGuardado);
    }

    //Actualizar Administrador
    @Test
    @Sql("classpath:dbInserts.sql")
    public void actualizarAdministradorTest(){

        Administrador guardado = administradorRepo.findById(1005).orElse(null);
        System.out.println("--Admin--: "+guardado);
        //modifico el Administrador
        guardado.setEmail("Ruiz@email.com");

        //guardo el administrador
        administradorRepo.save(guardado);

        Administrador AdminBuscado = administradorRepo.findById(1005).orElse(null);
        //verifico lo modificado
        Assertions.assertEquals("Ruiz@email.com", AdminBuscado.getEmail());
        System.out.println("--Admin--: "+AdminBuscado);

    }

    //Eliminar Administrador
    @Test
    @Sql("classpath:dbInserts.sql")
    public void eliminarAdministradorTest(){
        //Elimino el administrador
        administradorRepo.deleteById(1004);

        Administrador adminBuscado = administradorRepo.findById(1004).orElse(null);

        Assertions.assertNull(adminBuscado);
        System.out.println("Administrador Eliminado");

    }

    //Listar Administrador
    @Test
    @Sql("classpath:dbInserts.sql")
    public void listarAdministradorTest(){
        //Listar los administradores
        List<Administrador> administradores = administradorRepo.findAll();

        //Imprimir la lista de los Administradores
        administradores.forEach(u -> System.out.println(u));
    }
}
