package pe.edu.cibertec.proyecto_app_web.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
public class Producto extends DomainObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    Long id;
    @Setter
    String nombre;
    @Setter
    String autor;
    @Setter
    Integer stock;
    @Setter
    BigDecimal precioUnitario;

    public Producto(String nombre, String autor, Integer stock, BigDecimal precioUnitario) {
        this.nombre = nombre;
        this.autor = autor;
        this.stock = stock;
        this.precioUnitario = precioUnitario;

    }

    public Producto() {

    }

}
