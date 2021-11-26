package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Subasta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * En esta interface se realiza la relacion con la clase que se pretende realizar las pruebas, extiende de JpaRepository
 * para utilizar los metodos que esta nos facilita como el save.
 * <p>
 * Integrantes:
 * Juan Sebastian Tobon Alcaraz
 * Sebastian Londoño
 * Rodrigo Acosta Restrepo
 */

@Repository
public interface SubastaRepo extends JpaRepository<Subasta, Integer> {

    @Query("select max(dS.valor) from Subasta s join s.detalleSubasta dS where s.codigo = :codigo")
    Float obtnValorMasAlto(Integer codigo);

    @Query("select s from Subasta s where current_timestamp < s.fecha_limite")
    Float listarSubastasDisponibles(Integer codigo);

}
