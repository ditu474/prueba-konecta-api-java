package co.danielrg.pruebakonectajava.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import co.danielrg.pruebakonectajava.entities.Empleado;

public interface EmpleadoDao extends JpaRepository<Empleado, Long>{

}
