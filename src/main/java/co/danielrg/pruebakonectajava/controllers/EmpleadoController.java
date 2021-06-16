package co.danielrg.pruebakonectajava.controllers;

import co.danielrg.pruebakonectajava.dtos.Empleado.EmpleadoRequest;
import co.danielrg.pruebakonectajava.dtos.Empleado.EmpleadoResponse;
import co.danielrg.pruebakonectajava.entities.Empleado;
import co.danielrg.pruebakonectajava.services.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/api/v1")
public class EmpleadoController {

	private EmpleadoService empleadoService;

	@Autowired
	public EmpleadoController(EmpleadoService empleadoService) {
		this.empleadoService = empleadoService;
	}

	@GetMapping("/empleados")
	public List<EmpleadoResponse> getAll() {
		return empleadoService.findAll()
				.stream()
				.map(EmpleadoResponse::fromEmpleado)
				.collect(Collectors.toList());
	}
	
	@PostMapping("/empleados")
	public ResponseEntity<Map<String, Object>> newEmpleado(@Valid @RequestBody EmpleadoRequest empleadoRequest, BindingResult result) {
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

		Empleado empleado = empleadoRequest.toEmpleado();

		try {
			Empleado newEmpleado = empleadoService.save(empleado);
			EmpleadoResponse empleadoResponse = EmpleadoResponse.fromEmpleado(newEmpleado);

			response.put("mensaje", "Empledo creado con exito");
			response.put("empleado", empleadoResponse);
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al crear el empleado");
			response.put("error", e.getMessage()+": "+e.getMostSpecificCause().getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
