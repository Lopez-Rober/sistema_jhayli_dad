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

import com.jhayli.system.dtos.CreateRecepcionDto;
import com.jhayli.system.dtos.RecepcionDto;
import com.jhayli.system.mappers.RecepcionMapper;
import com.jhayli.system.models.RecepcionModel;
import com.jhayli.system.models.RolModel;
import com.jhayli.system.responses.GetListResponse;
import com.jhayli.system.services.RecepcionService;

@RestController
@RequestMapping("/api/recepciones")
public class RecepcionApi {
	@Autowired
    private RecepcionService recepcionService;
	
	@GetMapping
	public ResponseEntity<GetListResponse<RecepcionDto>> getRecepciones(@RequestParam(defaultValue = "10") int limit, @RequestParam(defaultValue = "0") int offset) {
		int count = recepcionService.count();
		List<RecepcionModel> recepciones = recepcionService.getRecepciones(limit, offset);
		
		GetListResponse<RecepcionDto> response = new GetListResponse<>();
		response.data = RecepcionMapper.toDtos(recepciones);
		response.count = response.data.size();
		response.totalCount = count;
		return ResponseEntity.ok(response);
	}
	
	@PostMapping
	public ResponseEntity<RecepcionDto> createRecepcion(@RequestBody CreateRecepcionDto recepcionDto) {
		return ResponseEntity.ok(RecepcionMapper.toDto(recepcionService.createRecepcion(recepcionDto)));
	}
	
	@GetMapping("/{recepcionId}")
	public ResponseEntity<RecepcionDto> getRecepcion(@PathVariable String recepcionId) {
		return ResponseEntity.ok(RecepcionMapper.toDto(recepcionService.getRecepcion(recepcionId)));
	}
	
	@PatchMapping("/{recepcionId}")
	public ResponseEntity<RecepcionDto> updateRecepcion(@PathVariable String recepcionId, @RequestBody CreateRecepcionDto recepcionDto) {
		return ResponseEntity.ok(RecepcionMapper.toDto(recepcionService.updateRecepcion(recepcionId, recepcionDto)));
	}
	
	@DeleteMapping("/{recepcionId}")
	public ResponseEntity<RecepcionDto> deleteRecepcion(@PathVariable String recepcionId) {
		return ResponseEntity.ok(RecepcionMapper.toDto(recepcionService.deleteRecepcion(recepcionId)));
	}
}
