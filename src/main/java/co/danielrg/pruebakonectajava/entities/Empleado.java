package co.danielrg.pruebakonectajava.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "empleado")
public class Empleado implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name = "fecha_ingreso")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaIngreso;

	@Column(nullable = false)
	private String nombre;

	@Column(nullable = false)
	private Float salario;

	public Empleado() {
	}

	public Empleado(String nombre, Float salario) {
		this.nombre = nombre;
		this.salario = salario;
	}

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

