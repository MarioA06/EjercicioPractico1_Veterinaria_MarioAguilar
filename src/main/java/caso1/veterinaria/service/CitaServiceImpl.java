/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package caso1.veterinaria.service;

import caso1.veterinaria.domain.Cita;
import caso1.veterinaria.repository.CitaRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CitaServiceImpl implements CitaService {

    private final CitaRepository citaRepositorio;

    @Override
    @Transactional(readOnly = true)
    public List<Cita> consultarCitas() {
        return citaRepositorio.findAll();
    }
}