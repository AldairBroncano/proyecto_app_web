package pe.edu.cibertec.proyecto_app_web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import pe.edu.cibertec.proyecto_app_web.repository.UserRepository;
import pe.edu.cibertec.proyecto_app_web.service.MiPropioUserDetailsService;

@Configuration
public class SecurityConfig {
    // Autenticacion 2 cosas

    // usuario y el password

    // 1 tu password que algoritmo usaras
    @Bean
    public PasswordEncoder passwordEncoder() {
        // return new BCryptPasswordEncoder();
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    // 2 una forma de obtener los datos de un usuario
    // @Bean
    // UserDetailsService userDetailsService(UserRepository userRepository) {
    // return new MiPropioUserDetailsService(userRepository);
    // // System.out.println("Codificando:" + passwordEncoder.encode("123456"));

    // // // en memoria, en jdbc (bd)
    // // List<UserDetails> users = List.of(
    // // new User("arthur", passwordEncoder.encode("123456"), List.of()),
    // // new User("diana", passwordEncoder.encode("719312"), List.of())
    // // );

    // // return new InMemoryUserDetailsManager(users);
    // }

    // @Bean
    // public PersonaController personaController(PersonaRepository
    // personaRepository) {
    // return new PersonaController(personaRepository);
    // }

    // Authorization
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(autorizar -> autorizar
                // .requestMatchers("personas/nueva").denyAll()
                .requestMatchers("productos/nueva", "pedido/eliminar").hasRole("ADMIN")
                .requestMatchers("personas/nueva", "pedido/eliminar").hasRole("ADMIN")
                .requestMatchers("pedidos/nuevo", "pedido/eliminar").hasRole("ADMIN")
                .requestMatchers("pedido/editar").hasAnyRole("ADMIN", "VENDEDOR")
                .anyRequest().permitAll())
                .formLogin(login -> login

                        .defaultSuccessUrl("/login-success", true) // Redirige al método que guarda el nombre en sesión
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                        .permitAll())
                .build();
    }

}
