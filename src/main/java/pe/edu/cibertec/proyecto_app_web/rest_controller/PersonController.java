package pe.edu.cibertec.proyecto_app_web.rest_controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import pe.edu.cibertec.proyecto_app_web.entities.Persona;
import pe.edu.cibertec.proyecto_app_web.repository.PersonaRepository;

@RestController
@RequestMapping("api/personas")
@AllArgsConstructor
public class PersonController {
    // CRUD
    PersonaRepository personaRepository;

    @GetMapping
    public List<Persona> list() {
        return personaRepository.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Persona> findById(@PathVariable Long id) {
        return ResponseEntity.of(personaRepository.findById(id));
    }

    @PostMapping
    public Persona register(@RequestBody Persona persona) {
        return personaRepository.save(persona);
    }

}
