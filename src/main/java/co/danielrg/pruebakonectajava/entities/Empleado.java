package co.danielrg.pruebakonectajava.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Entity
@Table(name = "empleado")
public class Empleado implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name = "fecha_ingreso")
	@Temporal(TemporalType.DATE)
	private Date fechaIngreso;

	@Column(nullable = false)
	@NotBlank(message = "El nombre no puede estar vacio")
	@Size(min = 3, max = 50, message = "El nombre debe tener mínimo 3 caracteres y máximo 50")
	private String nombre;

	@NotNull(message = "El salario no puede estar vacio")
	@PositiveOrZero(message = "El salario debe ser mayor o igual a cero")
	private Float salario;

	@PrePersist
	public void prePersist() {
		this.fechaIngreso = new Date();
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

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}

