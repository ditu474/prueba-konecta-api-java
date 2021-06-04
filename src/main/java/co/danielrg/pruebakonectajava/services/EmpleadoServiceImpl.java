package co.danielrg.pruebakonectajava.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.danielrg.pruebakonectajava.dao.EmpleadoDao;
import co.danielrg.pruebakonectajava.entities.Empleado;

@Service
public class EmpleadoServiceImpl implements EmpleadoService{

	@Autowired
	private EmpleadoDao empleadoDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Empleado> findAll() {
		return empleadoDao.findAll();
	}

	@Override
	@Transactional()
	public Empleado save(Empleado empleado) {
		return empleadoDao.save(empleado);
	}

	@Override
	public Empleado findById(Long id) {
		return empleadoDao.findById(id).orElse(null);
	}

	
}
