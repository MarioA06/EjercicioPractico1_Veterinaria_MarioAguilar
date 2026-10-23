package caso1.veterinaria.controllers;

import caso1.veterinaria.domain.TipoServicio;
import caso1.veterinaria.service.TipoServicioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/tipos")
public class TipoServicioController {

    private final TipoServicioService gestionTipos;

    @GetMapping
    public String mostrarTipos(Model modelo) {
        modelo.addAttribute("tiposAtencion", gestionTipos.consultarTipos());
        return "tipos/listado";
    }

    @GetMapping("/nuevo")
    public String prepararTipo(Model modelo) {
        modelo.addAttribute("tipoAtencion", new TipoServicio());
        return "tipos/formulario";
    }

    @GetMapping("/editar/{codigo}")
    public String editarTipo(@PathVariable("codigo") Integer codigo,
            Model modelo, RedirectAttributes avisos) {

        var resultado = gestionTipos.buscarTipo(codigo);

        if (resultado.isEmpty()) {
            avisos.addFlashAttribute("error",
                    "No se encontró el tipo de servicio.");
            return "redirect:/tipos";
        }

        modelo.addAttribute("tipoAtencion", resultado.get());
        return "tipos/formulario";
    }

    @PostMapping("/guardar")
    public String guardarTipo(
            @Valid @ModelAttribute("tipoAtencion") TipoServicio tipoAtencion,
            BindingResult validacion,
            RedirectAttributes avisos) {

        if (validacion.hasErrors()) {
            return "tipos/formulario";
        }

        boolean esNuevo = tipoAtencion.getCodigoTipo() == null;
        gestionTipos.registrarTipo(tipoAtencion);

        avisos.addFlashAttribute("exito", esNuevo
                ? "Tipo de servicio agregado correctamente."
                : "Tipo de servicio actualizado correctamente.");

        return "redirect:/tipos";
    }

    @PostMapping("/eliminar")
    public String borrarTipo(@RequestParam("codigoTipo") Integer codigoTipo,
            RedirectAttributes avisos) {

        try {
            gestionTipos.eliminarTipo(codigoTipo);
            avisos.addFlashAttribute("exito",
                    "Tipo de servicio eliminado correctamente.");
        } catch (IllegalArgumentException | IllegalStateException e) {
            avisos.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/tipos";
    }
}