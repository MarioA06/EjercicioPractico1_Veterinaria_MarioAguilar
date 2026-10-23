/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package caso1.veterinaria.service;

import caso1.veterinaria.domain.Servicio;
import caso1.veterinaria.repository.ServicioRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor

public class ServicioServiceImpl implements ServicioService {
    
    private final ServicioRepository servicioRepositorio;

    @Override
    @Transactional(readOnly = true)
    public List<Servicio> consultarServicios() {
        return servicioRepositorio.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Servicio> buscarServicio(Integer codigoServicio) {
        return servicioRepositorio.findById(codigoServicio);
    }

    @Override
    @Transactional
    public void registrarServicio(Servicio atencionVeterinaria) {
        servicioRepositorio.save(atencionVeterinaria);
    }

    @Override
    @Transactional
    public void eliminarServicio(Integer codigoServicio) {
        if (!servicioRepositorio.existsById(codigoServicio)) {
            throw new IllegalArgumentException(
                    "El servicio seleccionado no existe.");
        }

        try {
            servicioRepositorio.deleteById(codigoServicio);
            servicioRepositorio.flush();
        } catch (DataIntegrityViolationException e) {
            throw new IllegalStateException(
                    "No se puede eliminar este servicio porque tiene citas asociadas.",
                    e);
        }
    }
}
    

