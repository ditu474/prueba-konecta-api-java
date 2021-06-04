package co.danielrg.pruebakonectajava.services;

import java.util.List;

import co.danielrg.pruebakonectajava.entities.Empleado;

public interface EmpleadoService {

	public List<Empleado> findAll();
	
	public Empleado findById(Long id);
	
	public Empleado save(Empleado empleado);
}
