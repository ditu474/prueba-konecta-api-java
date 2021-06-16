package co.danielrg.pruebakonectajava.services;

import co.danielrg.pruebakonectajava.entities.Empleado;
import co.danielrg.pruebakonectajava.repositories.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmpleadoServiceImpl implements EmpleadoService{

	private EmpleadoRepository empleadoRepository;

	@Autowired
	public EmpleadoServiceImpl(EmpleadoRepository empleadoRepository) {
		this.empleadoRepository = empleadoRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Empleado> findAll() {
		return empleadoRepository.findAll();
	}

	@Override
	@Transactional()
	public Empleado save(Empleado empleado) {
		return empleadoRepository.save(empleado);
	}

	@Override
	public Empleado findById(Long id) {
		return empleadoRepository.findById(id).orElse(null);
	}

	
}
