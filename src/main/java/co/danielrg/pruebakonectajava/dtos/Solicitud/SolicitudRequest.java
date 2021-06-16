package co.danielrg.pruebakonectajava.dtos.Solicitud;

import co.danielrg.pruebakonectajava.entities.Solicitud;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class SolicitudRequest {

    @NotBlank(message = "La descripicion no puede estar vacia")
    @Size(min = 3, max = 50, message = "La descripicion debe tener mínimo 3 caracteres y máximo 50")
    private String descripcion;

    @NotBlank(message = "El resumen no puede estar vacia")
    @Size(min = 3, max = 50, message = "El resumen debe tener mínimo 3 caracteres y máximo 50")
    private String resumen;

    @Positive(message = "El id del empleado debe ser un numero mayor a cero")
    @NotNull(message = "El id del empleado no puede estar vacio")
    private Long empleadoId;

    public Solicitud toSolicitud() {
        return new Solicitud(this.descripcion, this.resumen);
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public Long getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(Long empleadoId) {
        this.empleadoId = empleadoId;
    }
}
