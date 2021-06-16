package co.danielrg.pruebakonectajava.controllers;

import co.danielrg.pruebakonectajava.dtos.Solicitud.SolicitudRequest;
import co.danielrg.pruebakonectajava.dtos.Solicitud.SolicitudResponse;
import co.danielrg.pruebakonectajava.entities.Empleado;
import co.danielrg.pruebakonectajava.entities.Solicitud;
import co.danielrg.pruebakonectajava.services.EmpleadoService;
import co.danielrg.pruebakonectajava.services.SolicitudService;
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
public class SolicitudController {

	@Autowired
	private SolicitudService solicitudService;
	@Autowired
	private EmpleadoService empleadoService;
	
	@GetMapping("/solicitudes")
	public List<SolicitudResponse> getAll() {
		return solicitudService.findAll()
				.stream()
				.map(SolicitudResponse::fromSolicitud)
				.collect(Collectors.toList());
	}
	
	@PostMapping("/solicitudes")
	public ResponseEntity<Map<String, Object>> newEmpleado(@Valid @RequestBody SolicitudRequest solicitudRequest, BindingResult result) {
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

		Solicitud solicitud = solicitudRequest.toSolicitud();

		try {
			Empleado empleado = empleadoService.findById(solicitudRequest.getEmpleadoId());
			
			if(empleado == null) {
				response.put("mensaje", "Error al crear la solicitud");
				response.put("error", "No se encontró un empleado con la id: "+solicitudRequest.getEmpleadoId());
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}
			
			solicitud.setEmpleado(empleado);
			Solicitud nuevaSolicitud = solicitudService.save(solicitud);

			response.put("mensaje", "Solicitud creada con exito");
			response.put("solicitud", nuevaSolicitud);
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al crear la solicitud");
			response.put("error", e.getMessage()+": "+e.getMostSpecificCause().getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
