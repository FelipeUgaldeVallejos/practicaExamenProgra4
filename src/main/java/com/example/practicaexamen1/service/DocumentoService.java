package com.example.practicaexamen1.service;

import com.example.practicaexamen1.model.*;
import com.example.practicaexamen1.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//controlador llama al servicio, y el servicio llama al repositorio.

//autowired es literal Este objeto lo necesito, créalo tú y dámelo listo
//osea en vez de esto Servicio servicio = new Servicio(); se hace esto @Autowired Servicio servicio;

@Service
public class DocumentoService {

    @Autowired
    private TipoRepository tipoRepository;

    @Autowired
    private DocumentoRepository documentoRepository;

    @Autowired
    private LineaRepository lineaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Tipo> getTodosLosTipos() {
        return tipoRepository.findAll();
    }

    public List<Documento> getDocumentosPorTipo(String tipoId) {
        return documentoRepository.findByTipoId(tipoId);
    }

    public List<Linea> getLineasDeUsuario(String usuarioId) {
        return lineaRepository.findByUsuarioId(usuarioId);
    }

    public void agregarDocumento(String usuarioId, String documentoId) {
        // si ya existe esa línea para ese usuario y documento
        Optional<Linea> lineaExistente = lineaRepository.findByUsuarioIdAndDocumentoId(usuarioId, documentoId);

        if (lineaExistente.isPresent()) {
            // existe entonces solo aumentamos la cantidad
            Linea linea = lineaExistente.get();
            linea.setCantidad(linea.getCantidad() + 1);
            lineaRepository.save(linea);
        } else {
            // no existe entonces creamos una nueva línea
            Usuario usuario = usuarioRepository.findById(usuarioId)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            Documento documento = documentoRepository.findById(documentoId)
                    .orElseThrow(() -> new RuntimeException("Documento no encontrado"));

            Linea nuevaLinea = new Linea(usuario, documento, 1);
            lineaRepository.save(nuevaLinea);
        }
    }

    public Float calcularTotal(List<Linea> lineas) {
        float total = 0f;
        for (Linea linea : lineas) {
            total += linea.getMontoTotal();
        }
        return total;
    }
}
