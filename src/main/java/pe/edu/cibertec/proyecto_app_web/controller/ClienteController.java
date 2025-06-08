package pe.edu.cibertec.proyecto_app_web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;
import pe.edu.cibertec.proyecto_app_web.entities.Cliente;
import pe.edu.cibertec.proyecto_app_web.repository.ClienteRepository;

@Controller
@RequestMapping("clientes")
@AllArgsConstructor
public class ClienteController {

    ClienteRepository clienteRepository;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("listaClientes", clienteRepository.findAll());
        return "client/list";
    }

    @GetMapping("nuevo")
    public String showFormCreate(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "client/create";
    }

    @PostMapping("nuevo")
    public String create(Cliente cliente) {
        clienteRepository.save(cliente);
        return "redirect:/clientes";
    }

    @PostMapping("eliminar/{id}")
    public String delete(@PathVariable Long id) {
        clienteRepository.deleteById(id);
        return "redirect:/clientes";
    }

    @GetMapping("{id}")
    public String showFormEdit(@PathVariable Long id, Model model) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow();
        model.addAttribute("cliente", cliente);
        return "client/edit";
    }

    @PostMapping("{id}")
    public String edit(@PathVariable Long id, Cliente cliente) {
        Cliente clienteBd = clienteRepository.findById(id).orElseThrow();
        clienteBd.setNombre(cliente.getNombre());
        clienteBd.setApellido(cliente.getApellido());
        clienteRepository.save(clienteBd);
        return "redirect:/clientes";
    }
}
