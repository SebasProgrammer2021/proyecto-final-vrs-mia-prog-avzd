package co.edu.uniquindio.proyecto.entidades;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


/**
 * En la clase administrador tendremos los parametros
 * Integer codigo
 * String nombre
 * String email
 * String password
 * Esta clase heredara algunos atributos de persona
 * Integrantes:
 * Juan Sebastian Tobon Alcaraz
 * Sebastian Londoño
 * Rodrigo Acosta Restrepo
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(callSuper = true)
public class Administrador extends Persona implements Serializable{

    /**
     * Metodo constructor con argumentos
     * @param codigo
     * @param nombre
     * @param email
     * @param password
     */
    public Administrador(Integer codigo, String nombre, String email, String password) {
        super(codigo, nombre, email, password);
    }
}
