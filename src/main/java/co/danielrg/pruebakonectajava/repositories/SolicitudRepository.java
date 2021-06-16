package co.danielrg.pruebakonectajava.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.danielrg.pruebakonectajava.entities.Solicitud;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SolicitudRepository extends JpaRepository<Solicitud, Long>{

    @Query("select s from Solicitud s left join fetch s.empleado")
    public List<Solicitud> findAll();
}
