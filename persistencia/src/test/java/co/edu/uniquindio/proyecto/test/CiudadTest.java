package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CiudadTest {

    @Autowired
    private CiudadRepo ciudadRepo;

    //Registrar Ciudad
    @Test
    @Sql("classpath:dbInserts.sql")
    public void registrarCiudadTest(){
        //Creo una Ciudad
        Ciudad ciudad = new Ciudad(8,"Barranquilla");
        //Guardo la Ciudad
        Ciudad ciudadGuardado = ciudadRepo.save(ciudad);

        System.out.println(ciudadGuardado);
        //Verificaion
        Assertions.assertNotNull(ciudadGuardado);
    }

    //Actualizar Ciudad
    @Test
    @Sql("classpath:dbInserts.sql")
    public void actualizarCiudadTest(){

        Ciudad guardado = ciudadRepo.findById(4).orElse(null);
        System.out.println("--ciudad--: "+guardado);
        //modifico la ciudad
        guardado.setNombre("Bogota");

        //guardo la ciudad
        ciudadRepo.save(guardado);

        Ciudad ciadadBuscado = ciudadRepo.findById(4).orElse(null);
        //verifico lo modificado
        Assertions.assertEquals("Bogota", ciadadBuscado.getNombre());
        System.out.println("--ciudad--: "+ciadadBuscado);

    }

    //Eliminar Ciudad
    @Test
    @Sql("classpath:dbInserts.sql")
    public void eliminarCiudadTest(){
        //Elimino la Ciudad
        ciudadRepo.deleteById(2);
        //Busco la ciudad eliminada
        Ciudad ciudadBuscado = ciudadRepo.findById(2).orElse(null);

        Assertions.assertNull(ciudadBuscado);
        System.out.println("Administrador Eliminado");

    }

    //Listar Administrador
    @Test
    @Sql("classpath:dbInserts.sql")
    public void listarAdministradorTest(){
        //Listar las Ciuades
        List<Ciudad> ciudades = ciudadRepo.findAll();

        //Imprimir la lista de las Ciuades
        ciudades.forEach(u -> System.out.println(u));
    }
}
