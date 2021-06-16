package co.danielrg.pruebakonectajava.services;

import co.danielrg.pruebakonectajava.entities.Empleado;
import co.danielrg.pruebakonectajava.repositories.EmpleadoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.verify;

class EmpleadoServiceImplTest {

    private EmpleadoServiceImpl empleadoService;

    @Mock
    private EmpleadoRepository empleadoRepository;

    @Captor
    private ArgumentCaptor<Empleado> empleadoArgumentCaptor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        empleadoService = new EmpleadoServiceImpl(empleadoRepository);
    }

    @Test
    void itShouldCreateAnEmpleado() {
        //given
        Empleado empleado = new Empleado("TEST", 124324F);

        //when
        empleadoService.save(empleado);

        //Then
        then(empleadoRepository).should().save(empleadoArgumentCaptor.capture());
        Empleado empleadoArgumentCaptureValue = empleadoArgumentCaptor.getValue();
        assertThat(empleadoArgumentCaptureValue).isEqualTo(empleado);
        verify(empleadoRepository).save(any());
    }

    @Test
    void itShouldReturnNullIfNoEmpleadoIsFoundById() {
        //given
        Long empleadoId = 10L;
        given(empleadoRepository.findById(empleadoId)).willReturn(Optional.empty());

        //when
        Empleado empleadoResponse = empleadoService.findById(empleadoId);

        //Then
        ArgumentCaptor<Long> idArgumentCaptor = ArgumentCaptor.forClass(Long.class);
        then(empleadoRepository).should().findById(idArgumentCaptor.capture());
        assertThat(idArgumentCaptor.getValue()).isEqualTo(empleadoId);
        assertThat(empleadoResponse).isNull();
        verify(empleadoRepository).findById(anyLong());
    }
}