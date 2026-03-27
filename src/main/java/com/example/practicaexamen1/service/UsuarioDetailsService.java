package com.example.practicaexamen1.service;

import com.example.practicaexamen1.model.Usuario;
import com.example.practicaexamen1.repository.DocumentoRepository;
import com.example.practicaexamen1.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
        import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * SERVICIO: UsuarioDetailsService
 *
 * Spring Security necesita que le digamos CÓMO cargar un usuario desde nuestra BD
 * para poder verificar el login. Para esto implementamos la interfaz UserDetailsService.
 *
 * Spring Security llama automáticamente a loadUserByUsername() cuando alguien
 * intenta iniciar sesión. Nosotros buscamos el usuario en BD y retornamos un
 * objeto UserDetails que Spring Security entiende.
 */

@Service
public class UsuarioDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Buscar el usuario en la base de datos
        Usuario usuario = usuarioRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        // Crear un UserDetails con el nombre de usuario, contraseña y roles (en este caso, un rol genérico "USER")
        return new org.springframework.security.core.userdetails.User(
                usuario.getId(),
                usuario.getClave(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + usuario.getRol()))
        );
    }
}