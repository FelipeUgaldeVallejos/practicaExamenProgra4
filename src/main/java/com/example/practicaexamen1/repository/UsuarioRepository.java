package com.example.practicaexamen1.repository;

import com.example.practicaexamen1.model.Tipo;
import com.example.practicaexamen1.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//los repository son para obtener los datos de la base de datos, es decir, para hacer las consultas a la base de datos

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,String> {
}
