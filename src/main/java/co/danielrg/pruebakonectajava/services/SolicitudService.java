package co.danielrg.pruebakonectajava.services;

import java.util.List;

import co.danielrg.pruebakonectajava.entities.Solicitud;

public interface SolicitudService {
	
	public List<Solicitud> findAll();
	
	public Solicitud save(Solicitud solicitud);
}
