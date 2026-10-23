/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package caso1.veterinaria.service;

import caso1.veterinaria.domain.TipoServicio;
import java.util.List;
import java.util.Optional;

public interface TipoServicioService {

    List<TipoServicio> consultarTipos();

    Optional<TipoServicio> buscarTipo(Integer codigoTipo);

    void registrarTipo(TipoServicio tipoAtencion);

    void eliminarTipo(Integer codigoTipo);
}