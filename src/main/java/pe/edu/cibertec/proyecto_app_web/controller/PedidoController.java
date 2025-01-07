package pe.edu.cibertec.proyecto_app_web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;
import pe.edu.cibertec.proyecto_app_web.entities.Pedido;
import pe.edu.cibertec.proyecto_app_web.entities.Producto;
import pe.edu.cibertec.proyecto_app_web.repository.PedidoRepository;
import pe.edu.cibertec.proyecto_app_web.repository.PersonaRepository;
import pe.edu.cibertec.proyecto_app_web.repository.ProductoRepository;

@Controller
@RequestMapping("pedidos")
@AllArgsConstructor
public class PedidoController {

    PedidoRepository pedidoRepository;
    PersonaRepository personaRepository;
    ProductoRepository productoRepository;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("pedidos", pedidoRepository.findAll());
        return "pedido/list";
    }

    @GetMapping("/nuevo")
    public String showFormCreate(Model model) {
        // Agregar al modelo las listas de personas y productos
        model.addAttribute("pedido", new Pedido());
        model.addAttribute("personas", personaRepository.findAll()); // Lista de personas
        model.addAttribute("productos", productoRepository.findAll()); // Lista de productos
        return "pedido/create"; // Nombre de la vista (pedido/create.html)
    }

    @PostMapping("/nuevo")
    public String create(@ModelAttribute Pedido pedido) {
        Producto producto = productoRepository.findById(pedido.getProducto().getId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        if (producto.getStock() < pedido.getCantidad()) {
            // Manejo de error (redireccionar o mostrar mensaje en la vista)
            return "pedido/create";
        }
        producto.setStock(producto.getStock() - pedido.getCantidad());
        productoRepository.save(producto);
        pedidoRepository.save(pedido);
        return "redirect:/pedidos";
    }

    @PostMapping("eliminar/{id}")
    public String delete(@PathVariable Long id) {
        pedidoRepository.deleteById(id);
        return "redirect:/pedidos";
    }

    @GetMapping("{id}")
    public String showFormEdit(@PathVariable Long id, Model model) {
        Pedido pedido = pedidoRepository.findById(id).orElseThrow();
        model.addAttribute("pedido", pedido);
        model.addAttribute("personas", personaRepository.findAll());
        model.addAttribute("productos", productoRepository.findAll());
        return "pedido/edit";
    }

    @PostMapping("{id}")
    public String edit(@PathVariable Long id, Pedido pedido) {
        Pedido pedidoBd = pedidoRepository.findById(id).orElseThrow();
        pedidoBd.setPersona(pedido.getPersona());
        pedidoBd.setProducto(pedido.getProducto());
        pedidoBd.setCantidad(pedido.getCantidad());

        pedidoRepository.save(pedidoBd);
        return "redirect:/pedidos";
    }
}
