package co.danielrg.pruebakonectajava.controllers;

import co.danielrg.pruebakonectajava.dtos.Empleado.EmpleadoRequest;
import co.danielrg.pruebakonectajava.dtos.Empleado.EmpleadoResponse;
import co.danielrg.pruebakonectajava.entities.Empleado;
import co.danielrg.pruebakonectajava.services.EmpleadoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(EmpleadoController.class)
class EmpleadoControllerTest {
    private EmpleadoController empleadoController;
    private ObjectMapper mapper = new ObjectMapper();
    private final String ENDPOINT = "/api/v1/empleados";

    @MockBean
    private EmpleadoService empleadoService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        empleadoController = new EmpleadoController(empleadoService);
    }

    @Test
    void itShouldReturnAnErrorListIfEmpleadoRequestIsInvalid() throws Exception {
        //given
        String request = "{}";

        //When
        MvcResult mvcResult = mockMvc.perform(post(ENDPOINT)
                .content(request)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        //Then
        assertEquals(400, mvcResult.getResponse().getStatus());

        List<String> errors = new ArrayList<>();
        errors.add("El salario no puede estar vacio");
        errors.add("El nombre no puede estar vacio");
        String expectedResponse = "{\"errors\":"+mapper.writeValueAsString(errors)+"}";
        assertEquals(expectedResponse , mvcResult.getResponse().getContentAsString());

        then(empleadoService).shouldHaveNoInteractions();
    }

    @Test
    void itShouldReturnAnEmpleadoIfRequestIsSuccessful() throws Exception{
        //given
        EmpleadoRequest empleadoRequest = new EmpleadoRequest();
        empleadoRequest.setSalario(23532532F);
        empleadoRequest.setNombre("Test");
        String request = mapper.writeValueAsString(empleadoRequest);

        Empleado empleado = empleadoRequest.toEmpleado();
        empleado.setId(1L);
        empleado.setFechaIngreso(new Date());
        given(empleadoService.save(any())).willReturn(empleado);

        //when
        MvcResult mvcResult = mockMvc.perform(post(ENDPOINT)
                .content(request)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        //Then
        ArgumentCaptor<Empleado> empleadoArgumentCaptor = ArgumentCaptor.forClass(Empleado.class);
        then(empleadoService).should().save(empleadoArgumentCaptor.capture());
        assertThat(empleadoArgumentCaptor.getValue()).usingRecursiveComparison().isEqualTo(empleadoRequest.toEmpleado());

        verify(empleadoService).save(any());

        assertEquals(201, mvcResult.getResponse().getStatus());

        Map<String, Object> response = new HashMap<>();
        response.put("mensaje", "Empledo creado con exito");
        response.put("empleado", EmpleadoResponse.fromEmpleado(empleado));
        mapper.setDateFormat(new StdDateFormat().withColonInTimeZone(true));
        assertEquals(mapper.writeValueAsString(response), mvcResult.getResponse().getContentAsString());

        then(empleadoService).shouldHaveNoMoreInteractions();
    }

}