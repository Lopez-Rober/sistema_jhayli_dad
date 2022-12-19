package com.jhayli.system.utils;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.jhayli.system.models.UsuarioModel;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
	private static final long EXPIRE_DURATION = 24  * 60 * 60 * 1000; // 24h

	@Value("${app.jwt.secret}")
	private String secretKey;
	
	public String generate(UsuarioModel usuario) {
		return Jwts.builder()
				.setSubject(usuario.getId())
				.setIssuer("DSamiStore")
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
				.signWith(SignatureAlgorithm.HS512, secretKey)
				.compact();
	}
	
	public boolean validate(String token) {
		Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
		
		return true;
	}
	
	public String getSubject(String token) {
		return parseClaims(token).getSubject();
	}
	
	private Claims parseClaims(String token) {
		return Jwts.parser()
				.setSigningKey(secretKey)
				.parseClaimsJws(token)
				.getBody();
	}
}