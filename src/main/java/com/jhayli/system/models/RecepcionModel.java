package com.jhayli.system.models;

import java.util.Date;

import com.jhayli.system.enums.RecepcionEstadoEnum;

public class RecepcionModel {
	
	private String id;
	
	private String usuarioId;
	
	private UsuarioModel usuario;
	
	private String proveedorId;
	
	private ProveedorModel proveedor;
	
	private RecepcionEstadoEnum estado;
	
	private Date recibidoAt;
	
	private Date createdAt;
	
	private Date updatedAt;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(String usuarioId) {
		this.usuarioId = usuarioId;
	}

	public UsuarioModel getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioModel usuario) {
		this.usuario = usuario;
	}

	public String getProveedorId() {
		return proveedorId;
	}

	public void setProveedorId(String proveedorId) {
		this.proveedorId = proveedorId;
	}

	public ProveedorModel getProveedor() {
		return proveedor;
	}

	public void setProveedor(ProveedorModel proveedor) {
		this.proveedor = proveedor;
	}

	public RecepcionEstadoEnum getEstado() {
		return estado;
	}

	public void setEstado(RecepcionEstadoEnum estado) {
		this.estado = estado;
	}

	public Date getRecibidoAt() {
		return recibidoAt;
	}

	public void setRecibidoAt(Date recibidoAt) {
		this.recibidoAt = recibidoAt;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	
}
