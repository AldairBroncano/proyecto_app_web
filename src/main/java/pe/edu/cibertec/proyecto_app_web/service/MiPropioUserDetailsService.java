package pe.edu.cibertec.proyecto_app_web.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import pe.edu.cibertec.proyecto_app_web.entities.User;
import pe.edu.cibertec.proyecto_app_web.repository.UserRepository;

// @Component
@Service
public class MiPropioUserDetailsService implements UserDetailsService {

    UserRepository userRepository;

    public MiPropioUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // select * from user where name = username
        Optional<User> userOptional = userRepository.findByName(username); // userRepository.findAll().stream().filter(x
                                                                           // ->
                                                                           // x.getName().equals(username)).findFirst();
        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        return userOptional.get();
    }

}
