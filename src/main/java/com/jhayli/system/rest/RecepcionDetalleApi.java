package com.jhayli.system.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jhayli.system.dtos.CreateRecepcionDetalleDto;
import com.jhayli.system.models.RecepcionDetalleModel;
import com.jhayli.system.services.RecepcionDetalleService;

@RestController
@RequestMapping("/api/recepciones/{recepcionId}/detalles")
public class RecepcionDetalleApi {
	@Autowired
    private RecepcionDetalleService recepcionDetalleService;
	
	@GetMapping
	public ResponseEntity<List<RecepcionDetalleModel>> getRecepciones(@PathVariable String recepcionId, @RequestParam(defaultValue = "10") int limit, @RequestParam(defaultValue = "0") int offset) {
		return ResponseEntity.ok(recepcionDetalleService.getRecepcionDetalles(recepcionId, limit, offset));
	}
	
	@PostMapping
	public ResponseEntity<RecepcionDetalleModel> createRecepcion(@PathVariable String recepcionId, @RequestBody CreateRecepcionDetalleDto recepcionDetalleDto) {
		return ResponseEntity.ok(recepcionDetalleService.createRecepcionDetalle(recepcionId, recepcionDetalleDto));
	}
	
	@GetMapping("/{recepcionDetalleId}")
	public ResponseEntity<RecepcionDetalleModel> getRecepcion(@PathVariable String recepcionDetalleId) {
		return ResponseEntity.ok(recepcionDetalleService.getRecepcionDetalle(recepcionDetalleId));
	}
	
	@PatchMapping("/{recepcionDetalleId}")
	public ResponseEntity<RecepcionDetalleModel> updateRecepcion(@PathVariable String recepcionDetalleId, @RequestBody CreateRecepcionDetalleDto recepcionDetalleDto) {
		return ResponseEntity.ok(recepcionDetalleService.updateRecepcionDetalle(recepcionDetalleId, recepcionDetalleDto));
	}
	
	@DeleteMapping("/{recepcionDetalleId}")
	public ResponseEntity<RecepcionDetalleModel> deleteRecepcion(@PathVariable String recepcionDetalleId) {
		return ResponseEntity.ok(recepcionDetalleService.deleteRecepcionDetalle(recepcionDetalleId));
	}
}
