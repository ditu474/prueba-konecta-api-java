package co.danielrg.pruebakonectajava.services;

import co.danielrg.pruebakonectajava.entities.Solicitud;
import co.danielrg.pruebakonectajava.repositories.SolicitudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SolicitudServiceImpl implements SolicitudService{
	
	@Autowired
	private SolicitudRepository solicitudRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Solicitud> findAll() {
		return solicitudRepository.findAll();
	}

	@Override
	@Transactional()
	public Solicitud save(Solicitud solicitud) {
		return solicitudRepository.save(solicitud);
	}

}
