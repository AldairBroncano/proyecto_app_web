package pe.edu.cibertec.proyecto_app_web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import pe.edu.cibertec.proyecto_app_web.entities.User;
import pe.edu.cibertec.proyecto_app_web.repository.UserRepository;

@Component
public class UserInitializer {
    
@Autowired
    private UserRepository userRepository;
@Autowired
    private PasswordEncoder passwordEncoder;
@PostConstruct
public void init() {
    System.out.println("✅ Ejecutando init...");

    try {
        if (userRepository.findByName("aldair").isEmpty()) {
            User admin = new User();
            admin.setName("aldair");
            admin.setPassword(passwordEncoder.encode("prodigio"));
            admin.setRole("ADMIN");
            userRepository.save(admin);
        }

        if (userRepository.findByName("usuario").isEmpty()) {
            User user = new User();
            user.setName("usuario");
            user.setPassword(passwordEncoder.encode("user123"));
            user.setRole("USER");
            userRepository.save(user);
        }
    } catch (Exception e) {
        System.out.println("❌ ERROR en init(): " + e.getMessage());
        e.printStackTrace();
    }
}


}
