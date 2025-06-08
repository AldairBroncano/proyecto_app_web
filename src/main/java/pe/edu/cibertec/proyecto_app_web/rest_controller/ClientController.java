package pe.edu.cibertec.proyecto_app_web.rest_controller;

import java.util.List;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import pe.edu.cibertec.proyecto_app_web.entities.Cliente;
import pe.edu.cibertec.proyecto_app_web.repository.ClienteRepository;

@RestController
@RequestMapping("api/clientes")
@AllArgsConstructor
public class ClientController {
    // CRUD
    ClienteRepository clienteRepository;

    @GetMapping
    public List<Cliente> list() {
        return clienteRepository.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Cliente> findById(@PathVariable Long id) {
        return ResponseEntity.of(clienteRepository.findById(id));
    }

    @PostMapping
    public Cliente register(@RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }

}
