package pe.edu.cibertec.proyecto_app_web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import pe.edu.cibertec.proyecto_app_web.repository.UserRepository;
import pe.edu.cibertec.proyecto_app_web.service.MiPropioUserDetailsService;

@Configuration
public class SecurityConfig {

    // Codificador de contraseñas (bcrypt por defecto)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    // Servicio para cargar datos del usuario desde la BD
    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new MiPropioUserDetailsService(userRepository);
    }

    // Proveedor de autenticación que usa el userDetailsService y el encoder
    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    // Configuración de seguridad HTTP
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("productos/nueva", "personas/nueva", "pedidos/nuevo", "pedido/eliminar").hasRole("ADMIN")
                        .requestMatchers("pedido/editar").hasAnyRole("ADMIN", "USER")
                        .anyRequest().permitAll()
                )
                .formLogin(form -> form
                    //    .loginPage("/login") // si tienes una página personalizada
                        .defaultSuccessUrl("/login-success", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                        .permitAll()
                )
                .build();
    }
}
