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

    // Asignar un rol por defecto si no se indicó
    if (user.getRole() == null || user.getRole().isEmpty()) {
        user.setRole("USER"); // También podrías usar "ADMIN" si es un admin
    }

    userRepository.save(user);
    return "redirect:/";
}




@GetMapping("/login-success")
public String loginSuccess(HttpSession session) {
    var auth = SecurityContextHolder.getContext().getAuthentication();
    String username = auth.getName();

    session.setAttribute("nombreUsuario", username);

    boolean isAdmin = auth.getAuthorities().stream()
        .anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"));
    boolean isUser = auth.getAuthorities().stream()
        .anyMatch(role -> role.getAuthority().equals("ROLE_USER"));

    if (isAdmin) {
        session.setAttribute("rol", "ADMIN");
    } else if (isUser) {
        session.setAttribute("rol", "USER");
    }

    return "redirect:/";
}


}
