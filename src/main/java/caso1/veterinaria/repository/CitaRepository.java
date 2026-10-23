/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package caso1.veterinaria.repository;

import caso1.veterinaria.domain.Cita;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CitaRepository
        extends JpaRepository<Cita, Integer> {
}