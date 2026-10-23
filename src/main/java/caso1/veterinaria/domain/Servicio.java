package caso1.veterinaria.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "servicio")

public class Servicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer codigoServicio;
    
    @NotBlank(message = "Ingrese el nombre del servicio.")
    @Size(max = 100)
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombreServicio;
    
    @NotNull(message = "Ingrese el precio.")
    @DecimalMin(value = "0.01", message = "El precio debe ser mayor a cero.")
    @Digits(integer = 6, fraction = 2)
    @Column(name = "precio", nullable = false, precision = 8, scale = 2)
    private BigDecimal costoServicio;

    @NotNull(message = "Ingrese la duración.")
    @Min(value = 1, message = "La duración debe ser de al menos un minuto.")
    @Column(name = "duracion_minutos", nullable = false)
    private Integer duracionMinutos;

    @NotNull(message = "Seleccione un tipo de servicio.")
    @ManyToOne
    @JoinColumn(name = "tipo_servicio_id", nullable = false)
    private TipoServicio tipoServicio;
}
