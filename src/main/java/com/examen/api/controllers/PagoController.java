package com.examen.api.controllers;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.examen.api.entities.Pago;
import com.examen.api.reposities.PagoRepository;

@RestController
@RequestMapping(value = "/pagos")
public class PagoController {
	
	@Autowired
	PagoRepository repository;
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public Collection<Pago> getListaPagos(){
		Iterable<Pago> listaPagos = repository.findAll();
		
		return (Collection<Pago>) listaPagos;
	}
	
	@GetMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public Pago getPago(@PathVariable (name = "id") Long id) {
		Optional<Pago> pago = repository.findById(id);
		Pago result =null;
		if(pago.isPresent()) {
			result = pago.get();
		}
		return result;
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Pago createPago(@RequestBody Pago pago) {
		
		Pago nuevaPago =repository.save(pago);
		return nuevaPago;
	}
	
	@DeleteMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public void deletePago(@PathVariable(name = "id") Long id) {
	repository.deleteById(id);
	}
	
	@PutMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public Pago updatePago(@PathVariable(name = "id") Long id, @RequestBody Pago pago) {
			Optional<Pago> oPago = repository.findById(id);
			if (oPago.isPresent()) {
				Pago actual = oPago.get();
				actual.setId(id);
				actual.setFecha(pago.getFecha());
				actual.setMonto(pago.getMonto());
				actual.setNumero(pago.getNumero());
				actual.setEstado(pago.getEstado());
				Pago updatePago = repository.save(actual);
				return updatePago;
			}
			return null;
		}

}
