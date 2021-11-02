package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.repositorios.CategoriaRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CategoriaTest {

    @Autowired
    private CategoriaRepo categoriaRepo;
    //Registrar Categoria
    @Test
    @Sql("classpath:dbInserts.sql")
    public void registrarCategoriaTest(){

        //Creo una Categoria
        Categoria categoria = new Categoria(106,"Deporte");
        //Guardo la Categoria
        Categoria categoriaGuardado = categoriaRepo.save(categoria);

        System.out.println(categoriaGuardado);
        //Verificación
        Assertions.assertNotNull(categoriaGuardado);
    }

    //Actualizar Categoria
    @Test
    @Sql("classpath:dbInserts.sql")
    public void actualizarCategoriaTest(){

        Categoria guardado = categoriaRepo.findById(104).orElse(null);
        //modifico la Categoria
        guardado.setNombre("Otros");

        //guardo la categoria
        categoriaRepo.save(guardado);

        Categoria catBuscado = categoriaRepo.findById(104).orElse(null);
        //verifico lo modificado
        Assertions.assertEquals("Otros", catBuscado.getNombre());
        System.out.println("--Categoria--: "+catBuscado);

    }

    //Eliminar Categoria
    @Test
    @Sql("classpath:dbInserts.sql")
    public void eliminarCategoriaTest(){

        //Elimino la categoria "104"
        categoriaRepo.deleteById(104);

        //Busco la categoria eliminada
        Categoria catBuscado = categoriaRepo.findById(104).orElse(null);

        Assertions.assertNull(catBuscado);
        System.out.println("Categoria Eliminada");

    }

    //Listar Categorias
    @Test
    @Sql("classpath:dbInserts.sql")
    public void listarCategoriaTest(){
        //Listar las Categorias
        List<Categoria> categorias = categoriaRepo.findAll();

        //Imprimir la lista de categorias
        categorias.forEach(u -> System.out.println(u));
    }
}
