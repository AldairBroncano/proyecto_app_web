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
    public void init(){


if (userRepository.findByName("admin") == null) {
            User admin = new User();
            admin.setName("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole("ADMIN");
            userRepository.save(admin);
        }

        if (userRepository.findByName("usuario") == null) {
            User user = new User();
            user.setName("usuario");
            user.setPassword(passwordEncoder.encode("user123"));
            user.setRole("USER");
            userRepository.save(user);
        }


    }


}
