package co.danielrg.pruebakonectajava.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "solicitud")
public class Solicitud implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String codigo;

	@Column(nullable = false)
	private String descripcion;

	@Column(nullable = false)
	private String resumen;

	@JoinColumn(name = "id_empleado")
	@ManyToOne(fetch = FetchType.LAZY)
	private Empleado empleado;

	public Solicitud() {
	}

	public Solicitud(String descripcion, String resumen) {
		this.descripcion = descripcion;
		this.resumen = resumen;
	}

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
