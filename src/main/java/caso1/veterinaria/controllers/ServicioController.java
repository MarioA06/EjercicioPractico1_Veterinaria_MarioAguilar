package caso1.veterinaria.controllers;

import caso1.veterinaria.domain.Servicio;
import caso1.veterinaria.service.ServicioService;
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
@RequestMapping("/servicios")
public class ServicioController {

    private final ServicioService gestionServicios;
    private final TipoServicioService gestionTipos;

    @GetMapping
    public String mostrarServicios(Model modelo) {
        modelo.addAttribute("atenciones",
                gestionServicios.consultarServicios());
        return "servicios/listado";
    }

    @GetMapping("/nuevo")
    public String prepararServicio(Model modelo) {
        modelo.addAttribute("atencionVeterinaria", new Servicio());
        modelo.addAttribute("tiposDisponibles",
                gestionTipos.consultarTipos());
        return "servicios/formulario";
    }

    @GetMapping("/editar/{codigo}")
    public String editarServicio(@PathVariable("codigo") Integer codigo,
            Model modelo, RedirectAttributes avisos) {

        var resultado = gestionServicios.buscarServicio(codigo);

        if (resultado.isEmpty()) {
            avisos.addFlashAttribute("error",
                    "No se encontró el servicio.");
            return "redirect:/servicios";
        }

        modelo.addAttribute("atencionVeterinaria", resultado.get());
        modelo.addAttribute("tiposDisponibles",
                gestionTipos.consultarTipos());
        return "servicios/formulario";
    }

    @PostMapping("/guardar")
    public String guardarServicio(
            @Valid @ModelAttribute("atencionVeterinaria") Servicio atencionVeterinaria,
            BindingResult validacion,
            Model modelo,
            RedirectAttributes avisos) {

        if (atencionVeterinaria.getTipoServicio() == null
                || atencionVeterinaria.getTipoServicio().getCodigoTipo() == null) {

            validacion.rejectValue("tipoServicio", "tipo.requerido",
                    "Seleccione un tipo de servicio.");

        } else if (gestionTipos.buscarTipo(
                atencionVeterinaria.getTipoServicio().getCodigoTipo()).isEmpty()) {

            validacion.rejectValue("tipoServicio", "tipo.inexistente",
                    "El tipo de servicio seleccionado no existe.");
        }

        if (validacion.hasErrors()) {
            modelo.addAttribute("tiposDisponibles",
                    gestionTipos.consultarTipos());
            return "servicios/formulario";
        }

        boolean esNuevo = atencionVeterinaria.getCodigoServicio() == null;
        gestionServicios.registrarServicio(atencionVeterinaria);

        avisos.addFlashAttribute("exito", esNuevo
                ? "Servicio agregado correctamente."
                : "Servicio actualizado correctamente.");

        return "redirect:/servicios";
    }

    @PostMapping("/eliminar")
    public String borrarServicio(
            @RequestParam("codigoServicio") Integer codigoServicio,
            RedirectAttributes avisos) {

        try {
            gestionServicios.eliminarServicio(codigoServicio);
            avisos.addFlashAttribute("exito",
                    "Servicio eliminado correctamente.");
        } catch (IllegalArgumentException | IllegalStateException e) {
            avisos.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/servicios";
    }
}