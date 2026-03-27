package com.example.practicaexamen1.repository;

import com.example.practicaexamen1.model.Documento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//los repository son para obtener los datos de la base de datos, es decir, para hacer las consultas a la base de datos

@Repository
public interface DocumentoRepository extends  JpaRepository<Documento,String> {
        // Busca documentos cuyo documento.tipo.id == tipoId
        // (como `tipo` es una relación ManyToOne, no un String)
        List<Documento> findByTipoId(String tipoId);
}
