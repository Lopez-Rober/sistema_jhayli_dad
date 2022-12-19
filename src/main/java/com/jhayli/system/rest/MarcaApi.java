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

import com.jhayli.system.dtos.CreateMarcaDto;
import com.jhayli.system.models.CategoriaModel;
import com.jhayli.system.models.MarcaModel;
import com.jhayli.system.responses.GetListResponse;
import com.jhayli.system.services.MarcaService;

@RestController
@RequestMapping("/api/marcas")
public class MarcaApi {
	@Autowired
    private MarcaService marcaService;

	
	@GetMapping
	public ResponseEntity<GetListResponse<MarcaModel>> getMarcas(@RequestParam(defaultValue = "10") int limit, @RequestParam(defaultValue = "0") int offset, @RequestParam(defaultValue="")String query) {
		
		int count = marcaService.count(query);
		List<MarcaModel> marcas = marcaService.getMarcas(limit, offset,query);
		
		GetListResponse<MarcaModel> response = new GetListResponse<>();
		response.data = marcas;
		response.count = response.data.size();
		response.totalCount = count;
		return ResponseEntity.ok(response);
	}
	
	@PostMapping
	public ResponseEntity<MarcaModel> createMarca(@RequestBody CreateMarcaDto marcaDto) {
		return ResponseEntity.ok(marcaService.createMarca(marcaDto));
	}
	
	@GetMapping("/{marcaId}")
	public ResponseEntity<MarcaModel> getMarca(@PathVariable String marcaId) {
		return ResponseEntity.ok(marcaService.getMarca(marcaId));
	}
	
	@PatchMapping("/{marcaId}")
	public ResponseEntity<MarcaModel> updateMarca(@PathVariable String marcaId, @RequestBody CreateMarcaDto marcaDto) {
		return ResponseEntity.ok(marcaService.updateMarca(marcaId, marcaDto));
	}
	
	@DeleteMapping("/{marcaId}")
	public ResponseEntity<MarcaModel> deleteMarca(@PathVariable String marcaId) {
		return ResponseEntity.ok(marcaService.deleteMarca(marcaId));
	}
}
