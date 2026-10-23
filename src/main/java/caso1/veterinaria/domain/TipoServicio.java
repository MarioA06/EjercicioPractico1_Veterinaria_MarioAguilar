/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package caso1.veterinaria.domain;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tipo_servicio")

public class TipoServicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer codigoTipo;
    
    @NotBlank(message = "Ingrese el nombre del tipo de servicio.")
    @Size(max = 100)
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombreTipo;
    
    @Size(max = 255)
    @Column (name = "descripcion", length = 255)
    private String detalleTipo;
    
    @OneToMany(mappedBy = "tipoServicio")
    private List<Servicio> serviciosAsociados;
}
