package co.danielrg.pruebakonectajava.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.danielrg.pruebakonectajava.dao.SolicitudDao;
import co.danielrg.pruebakonectajava.entities.Solicitud;

@Service
public class SolicitudServiceImpl implements SolicitudService{
	
	@Autowired
	private SolicitudDao solicitudDao;

	@Override
	@Transactional(readOnly = true)
	public List<Solicitud> findAll() {
		return solicitudDao.findAll();
	}

	@Override
	@Transactional()
	public Solicitud save(Solicitud solicitud) {
		return solicitudDao.save(solicitud);
	}

}
