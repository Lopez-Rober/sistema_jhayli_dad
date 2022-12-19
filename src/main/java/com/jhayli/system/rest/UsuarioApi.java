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

import com.jhayli.system.dtos.CreateUsuarioDto;
import com.jhayli.system.dtos.UsuarioDto;
import com.jhayli.system.mappers.UsuarioMapper;
import com.jhayli.system.models.UsuarioModel;
import com.jhayli.system.responses.GetListResponse;
import com.jhayli.system.services.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioApi {
	
	@Autowired
    private UsuarioService usuarioService;

	@GetMapping
	public ResponseEntity<GetListResponse<UsuarioDto>> getUsuarios(@RequestParam(defaultValue = "10") int limit, @RequestParam(defaultValue = "0") int offset, @RequestParam(defaultValue = "") String query) {
		int count = usuarioService.count(query);
		List<UsuarioModel> usuarios = usuarioService.getUsuarios(limit, offset, query);
		
		GetListResponse<UsuarioDto> response = new GetListResponse<>();
		response.data = UsuarioMapper.toDtos(usuarios);
		response.count = response.data.size();
		response.totalCount = count;

		return ResponseEntity.ok(response);
	}
	
	@PostMapping
	public ResponseEntity<UsuarioDto> createUsuario(@RequestBody CreateUsuarioDto usuarioRequest) {
		UsuarioModel usuario = usuarioService.createUsuario(usuarioRequest);

		return ResponseEntity.ok(UsuarioMapper.toDto(usuario));
	}
	
	@GetMapping("/{usuarioId}")
	public ResponseEntity<UsuarioDto> getUsuario(@PathVariable String usuarioId) {
		UsuarioModel usuario = usuarioService.getUsuario(usuarioId);
		
		return ResponseEntity.ok(UsuarioMapper.toDto(usuario));
	}
	
	@PatchMapping("/{usuarioId}")
	public ResponseEntity<UsuarioDto> updateUsuario(@PathVariable String usuarioId, @RequestBody CreateUsuarioDto usuarioDto) {
		UsuarioModel usuario = usuarioService.updateUsuario(usuarioId, usuarioDto);
		
		return ResponseEntity.ok(UsuarioMapper.toDto(usuario));
	}
	
	@DeleteMapping("/{usuarioId}")
	public ResponseEntity<UsuarioDto> deleteUsuario(@PathVariable String usuarioId) {
		UsuarioModel usuario = usuarioService.deleteUsuario(usuarioId);
		
		return ResponseEntity.ok(UsuarioMapper.toDto(usuario));
	}
	
}