package co.danielrg.pruebakonectajava.dtos.Solicitud;

import co.danielrg.pruebakonectajava.entities.Empleado;
import co.danielrg.pruebakonectajava.entities.Solicitud;

public class SolicitudResponse {

    private Long id;
    private String codigo;
    private String descripcion;
    private String resumen;
    private Empleado empleado;

    public SolicitudResponse(Long id, String codigo, String descripcion, String resumen, Empleado empleado) {
        this.id = id;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.resumen = resumen;
        this.empleado = empleado;
    }

    public static SolicitudResponse fromSolicitud(Solicitud solicitud) {
        return new SolicitudResponse(
                solicitud.getId(),
                solicitud.getCodigo(),
                solicitud.getDescripcion(),
                solicitud.getResumen(),
                solicitud.getEmpleado()
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
}
