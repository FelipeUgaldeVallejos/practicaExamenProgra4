package com.example.practicaexamen1.controller;

import com.example.practicaexamen1.model.Linea;
import com.example.practicaexamen1.service.DocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/presentation/documentos")
public class DocumentoController {

    @Autowired
    private DocumentoService documentoService;

    @GetMapping("/show")
    public String mostrarDocumentos(
            @RequestParam(value = "tipoId", required = false) String tipoId,
            Authentication authentication,
            Model model) {

        // Obtenemos el ID del usuario logueado desde el objeto Authentication
        // authentication.getName() retorna el "username" que configuramos en UserDetailsService
        String usuarioId = authentication.getName();

        // Pasamos a la vista todos los tipos para el dropdown
        model.addAttribute("tipos", documentoService.getTodosLosTipos());

        // Si el usuario seleccionó un tipo, buscamos sus documentos
        if (tipoId != null && !tipoId.isEmpty()) {
            model.addAttribute("documentos", documentoService.getDocumentosPorTipo(tipoId));
            model.addAttribute("tipoSeleccionado", tipoId);
        }

        // Siempre cargamos MIS DOCUMENTOS del usuario (persistidos en BD)
        List<Linea> misLineas = documentoService.getLineasDeUsuario(usuarioId);
        model.addAttribute("misLineas", misLineas);
        model.addAttribute("total", documentoService.calcularTotal(misLineas));

        // El nombre del usuario se muestra en el menú (ej: "JPEREZ")
        model.addAttribute("usuarioId", usuarioId.toUpperCase());

        return "documentos";
    }

    @PostMapping("/agregar")
    public String agregarDocumento(
            @RequestParam("documentoId") String documentoId,
            @RequestParam(value = "tipoId", required = false) String tipoId,
            Authentication authentication) {

        String usuarioId = authentication.getName();

        // Delegamos la lógica al servicio
        documentoService.agregarDocumento(usuarioId, documentoId);

        // Redirigimos de vuelta a la página de documentos manteniendo el filtro
        // "redirect:" hace que el navegador haga un nuevo GET (patrón POST-Redirect-GET)
        // Esto evita que al refrescar la página se vuelva a enviar el formulario
        if (tipoId != null && !tipoId.isEmpty()) {
            return "redirect:/presentation/documentos/show?tipoId=" + tipoId;
        }
        return "redirect:/presentation/documentos/show";
    }
}
