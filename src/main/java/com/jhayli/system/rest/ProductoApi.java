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

import com.jhayli.system.dtos.CreateProductoDto;
import com.jhayli.system.models.ProductoModel;
import com.jhayli.system.responses.GetListResponse;
import com.jhayli.system.services.ProductoService;

@RestController
@RequestMapping("/api/productos")
public class ProductoApi {
	
	@Autowired
    private ProductoService productoService;
	
	@GetMapping
	public ResponseEntity<GetListResponse<ProductoModel>> getProductos(@RequestParam(defaultValue = "10") int limit, @RequestParam(defaultValue = "0") int offset ,@RequestParam(defaultValue="")String query) {
		int count = productoService.count(query);
		List<ProductoModel> producto = productoService.getProductos(limit,offset,query );
		GetListResponse<ProductoModel> response = new GetListResponse<>();
		response.data = producto;
		response.count = response.data.size();
		response.totalCount = count ;
		return ResponseEntity.ok(response);
	}
	
	@PostMapping
	public ResponseEntity<ProductoModel> createProducto(@RequestBody CreateProductoDto productoDto) {
		return ResponseEntity.ok(productoService.createProducto(productoDto));
	}
	
	@GetMapping("/{productoId}")
	public ResponseEntity<ProductoModel> getProducto(@PathVariable String productoId) {
		return ResponseEntity.ok(productoService.getProducto(productoId));
	}
	
	@PatchMapping("/{productoId}")
	public ResponseEntity<ProductoModel> updateProducto(@PathVariable String productoId, @RequestBody CreateProductoDto productoDto) {
		return ResponseEntity.ok(productoService.updateProducto(productoId, productoDto));
	}
	
	@DeleteMapping("/{productoId}")
	public ResponseEntity<ProductoModel> deleteProducto(@PathVariable String productoId) {
		return ResponseEntity.ok(productoService.deleteProducto(productoId));
	}
	
}