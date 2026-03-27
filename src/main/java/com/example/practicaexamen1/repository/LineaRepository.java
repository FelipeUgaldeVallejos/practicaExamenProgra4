package com.example.practicaexamen1.repository;

import com.example.practicaexamen1.model.Linea;
import com.example.practicaexamen1.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//los repository son para obtener los datos de la base de datos, es decir, para hacer las consultas a la base de datos

@Repository
public interface LineaRepository extends JpaRepository<Linea,Long> {

    List<Linea> findByUsuarioId(String usuarioId);

    Optional<Linea> findByUsuarioIdAndDocumentoId(String usuarioId, String documentoId);
}
