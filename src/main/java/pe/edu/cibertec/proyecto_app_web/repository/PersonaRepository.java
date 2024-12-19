package pe.edu.cibertec.proyecto_app_web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;

import pe.edu.cibertec.proyecto_app_web.entities.Persona;
import java.util.List;
import java.util.Optional;

public interface PersonaRepository extends JpaRepository<Persona, Long> {

    List<Persona> findByApellido(String apellido);

    List<Persona> readByApellido(String apellido);

    List<Persona> queryByApellido(String apellido);

    List<Persona> searchByApellido(String apellido);

    @Query("select p from Persona p where apellido = :apellido") // sql + java = jpql
    List<Persona> buscarPorApellido(String apellido);

    // @Query(value = "select * from persona where nombre = :nombre", nativeQuery =
    // true)
    // List<Persona> buscarPorNombre(String nombre);

    // select count() from persona where apellido = ?
    Long countByApellido(String apellido);

    // select * from where nombre = ? and apellido = ?
    Optional<Persona> findByNombreAndApellido(String nombre, String apellido);
}
