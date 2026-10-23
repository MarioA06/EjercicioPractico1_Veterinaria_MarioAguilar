/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package caso1.veterinaria.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ClinicaController {

    @GetMapping("/")
    public String mostrarPortada() {
        return "inicio";
    }

    @GetMapping("/consultas")
    public String mostrarContacto() {
        return "consultas/formulario";
    }

    @PostMapping("/consultas")
    public String recibirConsulta(
            @RequestParam("nombre") String nombre,
            @RequestParam("correo") String correo,
            @RequestParam("mascota") String mascota,
            @RequestParam("mensaje") String mensaje,
            RedirectAttributes avisos) {

        if (nombre.isBlank() || correo.isBlank()
                || mascota.isBlank() || mensaje.isBlank()) {

            avisos.addFlashAttribute("error",
                    "Complete todos los campos de la consulta.");

            return "redirect:/consultas";
        }

        avisos.addFlashAttribute("exito",
                "Gracias, " + nombre + ", por su consulta sobre "
                + mascota + ".");

        return "redirect:/consultas";
    }
}