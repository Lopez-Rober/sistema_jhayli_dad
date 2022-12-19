package com.jhayli.system.models;

import java.util.Date;

public class ProductoModel {
	private String id;
	
	private String nombre;
		
	private String descripcion;

	private Double precio;
	
	private String codigoBarras;

	private String categoriaId;
	
	private CategoriaModel categoria;
	
	private String marcaId;
	
	private MarcaModel marca;

    private Date createdAt;
	
	private Date updatedAt;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public String getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(String categoriaId) {
		this.categoriaId = categoriaId;
	}

	public CategoriaModel getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaModel categoria) {
		this.categoria = categoria;
	}

	public String getMarcaId() {
		return marcaId;
	}

	public void setMarcaId(String marcaId) {
		this.marcaId = marcaId;
	}
	
	public MarcaModel getMarca() {
		return marca;
	}

	public void setMarca(MarcaModel marca) {
		this.marca = marca;
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
	
	public ProductoModel simple() {
		this.categoria = null;;
		this.marca = null;;
		return this;
	}
}
