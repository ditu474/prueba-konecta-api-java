package co.danielrg.pruebakonectajava.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.danielrg.pruebakonectajava.entities.Empleado;
import co.danielrg.pruebakonectajava.services.EmpleadoService;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/api/v1")
public class EmpleadoController {

	@Autowired
	private EmpleadoService empleadoService;
	
	@GetMapping("/empleados")
	public List<Empleado> getAll() {
		return empleadoService.findAll();
	}
	
	@PostMapping("/empleados")
	public ResponseEntity<Map<String, Object>> newEmpleado(@Valid @RequestBody Empleado empleado, BindingResult result) {
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		Empleado newEmpleado = null;
		
		try {
			newEmpleado = empleadoService.save(empleado);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al crear el empleado");
			response.put("error", e.getMessage()+": "+e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		 
		response.put("mensaje", "Empledo creado con exito");
		response.put("empleado", newEmpleado);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
}
