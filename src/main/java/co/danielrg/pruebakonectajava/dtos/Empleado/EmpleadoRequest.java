package co.danielrg.pruebakonectajava.dtos.Empleado;

import co.danielrg.pruebakonectajava.entities.Empleado;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

public class EmpleadoRequest {
    @NotBlank(message = "El nombre no puede estar vacio")
    @Size(min = 3, max = 50, message = "El nombre debe tener mínimo 3 caracteres y máximo 50")
    private String nombre;

    @PositiveOrZero(message = "El salario debe ser mayor o igual a cero")
    @NotNull(message = "El salario no puede estar vacio")
    private Float salario;


    public Empleado toEmpleado() {
        Empleado empleado = new Empleado(nombre, salario);
        return empleado;
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
