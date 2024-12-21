package pe.edu.cibertec.proyecto_app_web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;
import pe.edu.cibertec.proyecto_app_web.entities.Producto;
import pe.edu.cibertec.proyecto_app_web.repository.ProductoRepository;

@Controller
@RequestMapping("productos")
@AllArgsConstructor
public class ProductoController {

    ProductoRepository productoRepository;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("listaProductos", productoRepository.findAll());
        return "product/list";
    }

    // @RequestMapping(path="personas/nueva", method=RequestMethod.GET)
    @GetMapping("nueva") // personas/nueva
    public String showFormCreate(Model model) {
        model.addAttribute("producto", new Producto());
        return "product/create";
    }

    // @RequestMapping(path="personas/nueva", method=RequestMethod.POST)
    @PostMapping("nueva")
    public String create(Producto producto) {
        productoRepository.save(producto);
        return "redirect:/productos";
    }

    @PostMapping("eliminar/{id}")
    public String delete(@PathVariable Long id) {
        productoRepository.deleteById(id);
        return "redirect:/productos";
    }

    @GetMapping("{id}")
    public String showFormEdit(@PathVariable Long id, Model model) {
        Producto producto = productoRepository.findById(id).orElseThrow();
        model.addAttribute("producto", producto);
        return "product/edit";
    }

    @PostMapping("{id}")
    public String edit(@PathVariable Long id, Producto producto) {
        Producto productoBd = productoRepository.findById(id).orElseThrow();
        productoBd.setNombre(producto.getNombre());
        productoBd.setAutor(producto.getAutor());
        productoBd.setStock(producto.getStock());
        productoBd.setPrecioUnitario(producto.getPrecioUnitario());
        productoRepository.save(productoBd);
        return "redirect:/productos";
    }

}
