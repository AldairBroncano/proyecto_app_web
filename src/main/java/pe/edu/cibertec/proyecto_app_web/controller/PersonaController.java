package pe.edu.cibertec.proyecto_app_web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;
import pe.edu.cibertec.proyecto_app_web.entities.Persona;
import pe.edu.cibertec.proyecto_app_web.repository.PersonaRepository;

// @Component
// @Service
// @Repository <= evitar usarlo
@Controller
@RequestMapping("personas")
@AllArgsConstructor
public class PersonaController {

    PersonaRepository personaRepository;

    // public PersonaController(PersonaRepository personaRepository) {
    // this.personaRepository = personaRepository;
    // }

    // @RequestMapping(path = "personas", method=RequestMethod.GET)
    @GetMapping
    public String list(Model model) {
        model.addAttribute("listaPersonas", personaRepository.findAll());
        return "person/list";
    }

    // @RequestMapping(path="personas/nueva", method=RequestMethod.GET)
    @GetMapping("nueva") // personas/nueva
    public String showFormCreate(Model model) {
        model.addAttribute("persona", new Persona());
        return "person/create";
    }

    // @RequestMapping(path="personas/nueva", method=RequestMethod.POST)
    @PostMapping("nueva")
    public String create(Persona persona) {
        personaRepository.save(persona);
        return "redirect:/personas";
    }

    @PostMapping("eliminar/{id}")
    public String delete(@PathVariable Long id) {
        personaRepository.deleteById(id);
        return "redirect:/personas";
    }

    @GetMapping("{id}")
    public String showFormEdit(@PathVariable Long id, Model model) {
        Persona persona = personaRepository.findById(id).orElseThrow();
        model.addAttribute("persona", persona);
        return "person/edit";
    }

    @PostMapping("{id}")
    public String edit(@PathVariable Long id, Persona persona) {
        Persona personaBd = personaRepository.findById(id).orElseThrow();
        personaBd.setNombre(persona.getNombre());
        personaBd.setApellido(persona.getApellido());
        personaRepository.save(personaBd);
        return "redirect:/personas";
    }
}
