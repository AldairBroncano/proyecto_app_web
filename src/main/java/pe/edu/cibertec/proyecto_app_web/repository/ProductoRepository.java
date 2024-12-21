package pe.edu.cibertec.proyecto_app_web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.cibertec.proyecto_app_web.entities.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
