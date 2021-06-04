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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.danielrg.pruebakonectajava.entities.Empleado;
import co.danielrg.pruebakonectajava.entities.Solicitud;
import co.danielrg.pruebakonectajava.services.EmpleadoService;
import co.danielrg.pruebakonectajava.services.SolicitudService;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/api/v1")
public class SolicitudController {

	@Autowired
	private SolicitudService solicitudService;
	@Autowired
	private EmpleadoService empleadoService;
	
	@GetMapping("/solicitudes")
	public List<Solicitud> getAll() {
		return solicitudService.findAll();
	}
	
	@PostMapping("/solicitudes")
	public ResponseEntity<Map<String, Object>> newEmpleado(@Valid @RequestBody Solicitud solicitud,@RequestParam(name = "idEmpleado", required = true) String id, BindingResult result) {
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		Solicitud nuevaSolicitud = null;
		
		try {
			Long idEmpleado = Long.parseLong(id);
			Empleado empleado = empleadoService.findById(idEmpleado);
			
			if(empleado == null) {
				response.put("mensaje", "Error al crear la solicitud");
				response.put("error", "No se encontró un empleado con la id: "+id);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
			}
			
			solicitud.setEmpleado(empleado);
			
			nuevaSolicitud = solicitudService.save(solicitud);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al crear la solicitud");
			response.put("error", e.getMessage()+": "+e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		 
		response.put("mensaje", "Solicitud creada con exito");
		response.put("solicitud", nuevaSolicitud);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
}
