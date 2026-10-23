/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package caso1.veterinaria.service;

import caso1.veterinaria.domain.TipoServicio;
import caso1.veterinaria.repository.TipoServicioRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor

public class TipoServicioServiceImpl implements TipoServicioService {
    
 private final TipoServicioRepository tipoRepositorio;

    @Override
    @Transactional(readOnly = true)
    public List<TipoServicio> consultarTipos() {
        return tipoRepositorio.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TipoServicio> buscarTipo(Integer codigoTipo) {
        return tipoRepositorio.findById(codigoTipo);
    }

    @Override
    @Transactional
    public void registrarTipo(TipoServicio tipoAtencion) {
        tipoRepositorio.save(tipoAtencion);
    }

    @Override
    @Transactional
    public void eliminarTipo(Integer codigoTipo) {
        if (!tipoRepositorio.existsById(codigoTipo)) {
            throw new IllegalArgumentException(
                    "El tipo de servicio seleccionado no existe.");
        }

        try {
            tipoRepositorio.deleteById(codigoTipo);
            tipoRepositorio.flush();
        } catch (DataIntegrityViolationException e) {
            throw new IllegalStateException(
                    "No se puede eliminar este tipo porque tiene servicios asociados.",
                    e);
        }
    }
}
