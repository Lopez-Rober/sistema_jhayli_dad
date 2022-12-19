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

import com.jhayli.system.dtos.CreateProveedorDto;
import com.jhayli.system.models.ProveedorModel;
import com.jhayli.system.responses.GetListResponse;
import com.jhayli.system.services.ProveedorService;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedorApi {
	@Autowired
    private ProveedorService proveedorService;
	
	@GetMapping
	public ResponseEntity<GetListResponse<ProveedorModel>> getProveedores(@RequestParam(defaultValue = "10") int limit, @RequestParam(defaultValue = "0") int offset , @RequestParam(defaultValue = "")String query) {
		int count = proveedorService.count(query);
		List<ProveedorModel> proveedores = proveedorService.getProveedores(limit, offset, query);
		GetListResponse<ProveedorModel> response = new GetListResponse<>();
		response.data = proveedores;
		response.count = response.data.size();
		response.totalCount = count;
		return ResponseEntity.ok(response);
	}
	
	
	@PostMapping
	public ResponseEntity<ProveedorModel> createProveedor(@RequestBody CreateProveedorDto proveedorDto) {
		return ResponseEntity.ok(proveedorService.createProveedor(proveedorDto));
	}
	
	@GetMapping("/{proveedorId}")
	public ResponseEntity<ProveedorModel> getProveedor(@PathVariable String proveedorId) {
		return ResponseEntity.ok(proveedorService.getProveedor(proveedorId));
	}
	
	@PatchMapping("/{proveedorId}")
	public ResponseEntity<ProveedorModel> updateProveedor(@PathVariable String proveedorId, @RequestBody CreateProveedorDto proveedorDto) {
		return ResponseEntity.ok(proveedorService.updateProveedor(proveedorId, proveedorDto));
	}
	
	@DeleteMapping("/{proveedorId}")
	public ResponseEntity<ProveedorModel> deleteProveedor(@PathVariable String proveedorId) {
		return ResponseEntity.ok(proveedorService.deleteProveedor(proveedorId));
	}
}
