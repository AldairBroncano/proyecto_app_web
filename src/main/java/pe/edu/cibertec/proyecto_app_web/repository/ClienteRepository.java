package pe.edu.cibertec.proyecto_app_web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;

import pe.edu.cibertec.proyecto_app_web.entities.Cliente;
import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findByApellido(String apellido);

    List<Cliente> readByApellido(String apellido);

    List<Cliente> queryByApellido(String apellido);

    List<Cliente> searchByApellido(String apellido);

    @Query("select p from Cliente p where apellido = :apellido") // sql + java = jpql
    List<Cliente> buscarPorApellido(String apellido);

    // @Query(value = "select * from Cliente where nombre = :nombre", nativeQuery =
    // true)
    // List<Cliente> buscarPorNombre(String nombre);

    // select count() from Cliente where apellido = ?
    Long countByApellido(String apellido);

    // select * from where nombre = ? and apellido = ?
    Optional<Cliente> findByNombreAndApellido(String nombre, String apellido);
}
