package pe.edu.cibertec.proyecto_app_web.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Pedido extends DomainObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne
    private Persona persona; // La persona que realiza el pedido

    @Setter
    @ManyToOne
    private Producto producto; // El producto solicitado

    private Integer cantidad; // Cantidad de productos solicitados

    // Fecha en que se realizó el pedido

    public Pedido() {
    }

    public Pedido(Persona persona, Producto producto, Integer cantidad) {
        this.persona = persona;
        this.producto = producto;
        this.cantidad = cantidad;

    }
}
