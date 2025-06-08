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
import pe.edu.cibertec.proyecto_app_web.repository.ClienteRepository;
import pe.edu.cibertec.proyecto_app_web.repository.ProductoRepository;

@Controller
@RequestMapping("pedidos")
@AllArgsConstructor
public class PedidoController {

    PedidoRepository pedidoRepository;
    ClienteRepository clienteRepository;
    ProductoRepository productoRepository;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("pedidos", pedidoRepository.findAll());
        return "pedido/list";
    }

    @GetMapping("/nuevo")
    public String showFormCreate(Model model) {
        model.addAttribute("pedido", new Pedido());
        model.addAttribute("clientes", clienteRepository.findAll()); // Lista de clientes
        model.addAttribute("productos", productoRepository.findAll()); // Lista de productos
        return "pedido/create";
    }

    @PostMapping("/nuevo")
    public String create(@ModelAttribute Pedido pedido, Model model) {
        Producto producto = productoRepository.findById(pedido.getProducto().getId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        if (producto.getStock() < pedido.getCantidad()) {
            model.addAttribute("error", "La cantidad solicitada supera el stock disponible.");
            model.addAttribute("clientes", clienteRepository.findAll());
            model.addAttribute("productos", productoRepository.findAll());
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
        model.addAttribute("clientes", clienteRepository.findAll());
        model.addAttribute("productos", productoRepository.findAll());
        return "pedido/edit";
    }

    @PostMapping("{id}")
    public String edit(@PathVariable Long id, Pedido pedido) {
        Pedido pedidoBd = pedidoRepository.findById(id).orElseThrow();
        pedidoBd.setCliente(pedido.getCliente());
        pedidoBd.setProducto(pedido.getProducto());
        pedidoBd.setCantidad(pedido.getCantidad());

        pedidoRepository.save(pedidoBd);
        return "redirect:/pedidos";
    }
}
