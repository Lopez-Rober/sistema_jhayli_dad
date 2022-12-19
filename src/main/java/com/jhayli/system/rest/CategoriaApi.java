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

import com.jhayli.system.dtos.CreateCategoriaDto;
import com.jhayli.system.models.CategoriaModel;
import com.jhayli.system.models.RolModel;
import com.jhayli.system.responses.GetListResponse;
import com.jhayli.system.services.CategoriaService;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaApi {
	@Autowired
    private CategoriaService categoriaService;
	
	@GetMapping
	public ResponseEntity<GetListResponse<CategoriaModel>> getCategorias(@RequestParam(defaultValue = "10") int limit, @RequestParam(defaultValue = "0") int offset, @RequestParam(defaultValue="")String query) {
		
		int count = categoriaService.count(query);
		List<CategoriaModel> categorias = categoriaService.getCategorias(limit, offset,query);
		
		GetListResponse<CategoriaModel> response = new GetListResponse<>();
		response.data = categorias;
		response.count = response.data.size();
		response.totalCount = count;
		return ResponseEntity.ok(response);
	}
	
	@PostMapping
	public ResponseEntity<CategoriaModel> createCategoria(@RequestBody CreateCategoriaDto categoriaDto) {
		return ResponseEntity.ok(categoriaService.createCategoria(categoriaDto));
	}
	
	@GetMapping("/{categoriaId}")
	public ResponseEntity<CategoriaModel> getCategoria(@PathVariable String categoriaId) {
		return ResponseEntity.ok(categoriaService.getCategoria(categoriaId));
	}
	
	@PatchMapping("/{categoriaId}")
	public ResponseEntity<CategoriaModel> updateCategoria(@PathVariable String categoriaId, @RequestBody CreateCategoriaDto categoriaDto) {
		return ResponseEntity.ok(categoriaService.updateCategoria(categoriaId, categoriaDto));
	}
	
	@DeleteMapping("/{categoriaId}")
	public ResponseEntity<CategoriaModel> deleteCategoria(@PathVariable String categoriaId) {
		return ResponseEntity.ok(categoriaService.deleteCategoria(categoriaId));
	}
}
