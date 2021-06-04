package co.danielrg.pruebakonectajava.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import co.danielrg.pruebakonectajava.entities.Solicitud;

public interface SolicitudDao extends JpaRepository<Solicitud, Long>{

}
