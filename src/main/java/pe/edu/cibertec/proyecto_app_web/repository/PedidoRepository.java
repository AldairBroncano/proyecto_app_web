package pe.edu.cibertec.proyecto_app_web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.cibertec.proyecto_app_web.entities.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}