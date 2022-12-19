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

import com.jhayli.system.dtos.CreateRolDto;
import com.jhayli.system.mappers.RolMapper;
import com.jhayli.system.models.RolModel;
import com.jhayli.system.responses.GetListResponse;
import com.jhayli.system.services.RolService;

@RestController
@RequestMapping("/api/roles")
public class RolApi {
	@Autowired
    private RolService rolService;
	
	@GetMapping
	public ResponseEntity<GetListResponse<RolModel>> getRoles(@RequestParam(defaultValue = "10") int limit, @RequestParam(defaultValue = "0") int offset, @RequestParam(defaultValue="")String query) {
		int count = rolService.count(query);
		List<RolModel> roles = rolService.getRoles(limit, offset,query);
		
		GetListResponse<RolModel> response = new GetListResponse<>();
		response.data = roles;
		response.count = response.data.size();
		response.totalCount = count;
		return ResponseEntity.ok(response);
	}
	
	@PostMapping
	public ResponseEntity<RolModel> createRol(@RequestBody CreateRolDto rolDto) {
		return ResponseEntity.ok(rolService.createRol(rolDto));
	}
	
	@GetMapping("/{rolId}")
	public ResponseEntity<RolModel> getCategoria(@PathVariable String rolId) {
		return ResponseEntity.ok(rolService.getRol(rolId));
	}
	
	@PatchMapping("/{rolId}")
	public ResponseEntity<RolModel> updateRol(@PathVariable String rolId, @RequestBody CreateRolDto rolDto) {
		return ResponseEntity.ok(rolService.updateRol(rolId, rolDto));
	}
	
	@DeleteMapping("/{rolId}")
	public ResponseEntity<RolModel> deleteCategoria(@PathVariable String rolId) {
		return ResponseEntity.ok(rolService.deleteRol(rolId));
	}
}
