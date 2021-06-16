package co.danielrg.pruebakonectajava.repositories;

import co.danielrg.pruebakonectajava.entities.Empleado;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.jdbc.Sql;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
class EmpleadoRepositoryTest {

    @Autowired
    EmpleadoRepository empleadoRepository;

    @Test
    void itShouldSaveAnEmpleado() {
        //given
        String nombre = "Daniel";
        Float salario = 123432.23F;
        Empleado empleado = new Empleado(nombre, salario);

        //when
        empleado = empleadoRepository.save(empleado);

        //Then
        Optional<Empleado> response = empleadoRepository.findById(empleado.getId());
        assertThat(response).isPresent().hasValueSatisfying(e -> {
            assertThat(e.getNombre()).isEqualTo(nombre);
            assertThat(e.getSalario()).isEqualTo(salario);
        });
    }

    @Test
    void itShouldNotSaveAnEmpleadoWhenNombreIsNull() {
        //given
        Float salario = 123432.23F;
        Empleado empleado = new Empleado(null, salario);

        //Then
        assertThatThrownBy(() -> empleadoRepository.save(empleado))
                .isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    void itShouldNotSaveAnEmpleadoWhenSalarioIsNull() {
        //given
        String nombre = "Daniel";
        Empleado empleado = new Empleado(nombre, null);

        //Then
        assertThatThrownBy(() -> empleadoRepository.save(empleado))
                .isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    void itShouldAddADateToFechaIngresoWhenEmpleadoIsSaved() {
        //given
        String nombre = "Daniel";
        Float salario = 123432.23F;
        Empleado empleado = new Empleado(nombre, salario);

        //when
        empleado = empleadoRepository.save(empleado);

        //Then
        Optional<Empleado> response = empleadoRepository.findById(empleado.getId());
        assertThat(response).isPresent().hasValueSatisfying(e -> {
            assertThat(e.getFechaIngreso()).isInstanceOf(Date.class);
        });
    }

    @Test
    @Sql("classpath:db/populateEmpleados.sql")
    void itShouldReturnAllEmpleadosSaved() {
        //when
        List<Empleado> empleados = empleadoRepository.findAll();

        //Then
        assertThat(empleados).hasSize(3);
    }
}