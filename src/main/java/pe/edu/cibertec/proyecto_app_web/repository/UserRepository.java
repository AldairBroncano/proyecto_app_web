package pe.edu.cibertec.proyecto_app_web.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.cibertec.proyecto_app_web.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    // buscar por nombre

    // findByAtributo => select * from where name = ?
    Optional<User> findByName(String name);
}
