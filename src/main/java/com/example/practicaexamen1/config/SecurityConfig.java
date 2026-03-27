package com.example.practicaexamen1.config;

import com.example.practicaexamen1.service.UsuarioDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UsuarioDetailsService usuarioDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // REGLAS DE ACCESO
                .authorizeHttpRequests(auth -> auth
                        // Recursos estáticos (imágenes, CSS) siempre son públicos
                        .requestMatchers("/images/**", "/css/**", "/favicon.ico").permitAll()
                        // La página de inicio es pública (la pueden ver sin login)
                        .requestMatchers("/", "/presentation/inicio").permitAll()
                        // El proceso de login es público (obvio)
                        .requestMatchers("/login", "/login?error").permitAll()
                        // Todo lo demás requiere estar autenticado
                        .anyRequest().authenticated()
                )

                // CONFIGURACION DEL FORMULARIO DE LOGIN
                .formLogin(form -> form
                        // URL donde está el formulario de login (nuestra página de inicio)
                        .loginPage("/")
                        // URL a donde se envía el formulario al presionar "Ingresar"
                        .loginProcessingUrl("/login")
                        // Nombre del campo del formulario para el usuario
                        .usernameParameter("id")
                        // Nombre del campo del formulario para la contraseña
                        .passwordParameter("clave")
                        // A dónde ir si el login fue exitoso
                        .defaultSuccessUrl("/presentation/documentos/show", true)
                        // A dónde ir si el login falló (regresa al inicio con ?error)
                        .failureUrl("/?error=true")
                        .permitAll()
                )

                // CONFIGURACION DEL LOGOUT
                .logout(logout -> logout
                        // URL para cerrar sesión (un link a /logout)
                        .logoutUrl("/logout")
                        // A dónde ir después del logout
                        .logoutSuccessUrl("/")
                        // Destruir la sesión completamente al salir
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                );

        return http.build();
    }

    /**
     * AuthenticationManager: le dice a Spring Security cómo autenticar usuarios.
     * Le indicamos que use nuestro UsuarioDetailsService y BCrypt.
     */
    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authBuilder
                .userDetailsService(usuarioDetailsService)
                .passwordEncoder(passwordEncoder());
        return authBuilder.build();
    }
}
