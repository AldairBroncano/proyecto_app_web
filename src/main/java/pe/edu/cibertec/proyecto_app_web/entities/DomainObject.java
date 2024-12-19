package pe.edu.cibertec.proyecto_app_web.entities;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

// Ver patron layer supertype (https://martinfowler.com/eaaCatalog/layerSupertype.html)
// 4 formas de herencia
// 3 primeras

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass // jpa
public abstract class DomainObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    // @CreationTimestamp <= hibernate
    @CreatedDate
    LocalDateTime fechaCreacion;
    // @UpdateTimestamp
    @LastModifiedDate
    LocalDateTime fechaActualizacion;

}
