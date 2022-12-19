package com.jhayli.system.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhayli.system.dtos.LoginDto;
import com.jhayli.system.models.UsuarioModel;
import com.jhayli.system.services.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthApi {
	
	@Autowired
    private AuthService authService;


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginRequest) {
		return ResponseEntity.ok(authService.login(loginRequest));
    }
    
    @PostMapping("/usuario")
    public ResponseEntity<UsuarioModel> getUsuario() {
		return ResponseEntity.ok(authService.getUsuario());
    }
}
