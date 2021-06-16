package co.danielrg.pruebakonectajava.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.danielrg.pruebakonectajava.entities.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long>{

}
