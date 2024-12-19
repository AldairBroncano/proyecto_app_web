package pe.edu.cibertec.proyecto_app_web.entities;

import java.math.BigDecimal;

import jakarta.persistence.Entity;

@Entity
public class Producto extends DomainObject {

    String nombre;
    String autor;
    Integer stock;
    BigDecimal precioUnitario;

}
