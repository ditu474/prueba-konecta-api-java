package co.danielrg.pruebakonectajava.entities;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "solicitud")
public class Solicitud implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String codigo;

	@Column(nullable = false)
	@NotBlank(message = "La descripicion no puede estar vacia")
	@Size(min = 3, max = 50, message = "La descripicion debe tener mínimo 3 caracteres y máximo 50")
	private String descripcion;

	@Column(nullable = false)
	@NotBlank(message = "El resumen no puede estar vacia")
	@Size(min = 3, max = 50, message = "El resumen debe tener mínimo 3 caracteres y máximo 50")
	private String resumen;

	@JoinColumn(name = "id_empleado")
	@ManyToOne(fetch = FetchType.EAGER)
	private Empleado empleado;

	@PrePersist
	public void prePersist() {
		this.codigo = UUID.randomUUID().toString();
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

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
