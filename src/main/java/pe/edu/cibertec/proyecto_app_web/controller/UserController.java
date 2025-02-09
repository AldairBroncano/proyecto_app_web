package pe.edu.cibertec.proyecto_app_web.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import pe.edu.cibertec.proyecto_app_web.entities.User;
import pe.edu.cibertec.proyecto_app_web.repository.UserRepository;

@Controller
@AllArgsConstructor
public class UserController {

    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    @GetMapping("signup")
    public String signUpShowForm(Model model) {
        model.addAttribute("usuario", new User());
        return "user/signup";
    }

    @PostMapping("signup")
    public String signUp(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "redirect:/";
    }

    @GetMapping("/login-success")
    public String loginSuccess(HttpSession session) {
        // Obtiene el usuario autenticado con Spring Security
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        // Guarda el nombre en la sesión para mostrarlo en la página principal
        session.setAttribute("nombreUsuario", username);

        return "redirect:/"; // Redirige a la página de inicio
    }

}
