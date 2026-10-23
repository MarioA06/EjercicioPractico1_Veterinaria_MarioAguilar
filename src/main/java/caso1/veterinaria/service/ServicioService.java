/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package caso1.veterinaria.service;

import caso1.veterinaria.domain.Servicio;
import java.util.List;
import java.util.Optional;

public interface ServicioService {
    
    List<Servicio> consultarServicios();
    Optional<Servicio> buscarServicio(Integer codigoServicio);
    
    void registrarServicio(Servicio atencionVeterinaria);
    void eliminarServicio(Integer codigoServicio);
    
    
    
}
