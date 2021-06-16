package co.danielrg.pruebakonectajava.dtos.Empleado;

import co.danielrg.pruebakonectajava.entities.Empleado;

import java.util.Date;

public class EmpleadoResponse {

    private Long id;
    private Date fechaIngreso;
    private String nombre;
    private Float salario;

    public EmpleadoResponse(Long id, Date fechaIngreso, String nombre, Float salario) {
        this.id = id;
        this.fechaIngreso = fechaIngreso;
        this.nombre = nombre;
        this.salario = salario;
    }

    public static EmpleadoResponse fromEmpleado(Empleado empleado) {
        EmpleadoResponse empleadoResponse = new EmpleadoResponse(
                empleado.getId(),
                empleado.getFechaIngreso(),
                empleado.getNombre(),
                empleado.getSalario()
        );

        return empleadoResponse;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Float getSalario() {
        return salario;
    }

    public void setSalario(Float salario) {
        this.salario = salario;
    }
}
