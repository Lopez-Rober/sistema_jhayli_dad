package com.jhayli.system.services.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jhayli.system.dtos.CreateProductoDto;
import com.jhayli.system.exceptions.BadRequestException;
import com.jhayli.system.exceptions.NotFoundException;
import com.jhayli.system.models.CategoriaModel;
import com.jhayli.system.models.MarcaModel;
import com.jhayli.system.models.ProductoModel;
import com.jhayli.system.repositories.CategoriaRepository;
import com.jhayli.system.repositories.MarcaRepository;
import com.jhayli.system.repositories.ProductoRepository;
import com.jhayli.system.services.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService {
	
	@Autowired
	private ProductoRepository productoRepository;

	@Autowired
	private MarcaRepository marcaRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Override
	public int count(String query) {
		return productoRepository.count(query);
		
	}
	
	@Override
	public List<ProductoModel> getProductos(int limit, int offset ,String query) {
		return productoRepository.findAll(limit, offset , query);
	}

	@Override
	public ProductoModel createProducto(CreateProductoDto productoDto) {
		ProductoModel productoNombreExists = productoRepository.findByNombre(productoDto.nombre);

		if (productoNombreExists != null) {
			throw new BadRequestException("Nombre ya existe");
		}

		ProductoModel productoCodigoBarrasExists = productoRepository.findByCodigoBarras(productoDto.codigoBarras);

		if (productoCodigoBarrasExists != null) {
			throw new BadRequestException("Codigo de barras ya existe");
		}
		
		MarcaModel marca = marcaRepository.findById(productoDto.marcaId);

		if (marca == null) {
			throw new BadRequestException("Marca no existe");
		}
		
		CategoriaModel categoria = categoriaRepository.findById(productoDto.categoriaId);

		if (categoria == null) {
			throw new BadRequestException("Categoria no existe");
		}
		
		ProductoModel producto = new ProductoModel();
		producto.setId(UUID.randomUUID().toString());
		producto.setNombre(productoDto.nombre);
		producto.setDescripcion(productoDto.descripcion);
		producto.setPrecio(productoDto.precio);
		producto.setCodigoBarras(productoDto.codigoBarras);
		producto.setMarcaId(productoDto.marcaId);
		producto.setMarca(marca);
		producto.setCategoriaId(productoDto.categoriaId);
		producto.setCategoria(categoria);
		producto.setCreatedAt(new Date());
		producto.setUpdatedAt(new Date());

		productoRepository.save(producto);

		return producto;
	}

	@Override
	public ProductoModel getProducto(String productoId) {
		ProductoModel producto = productoRepository.findById(productoId);

		if (producto == null) {
			throw new NotFoundException("Producto no existe");
		}

		return producto;
	}

	@Override
	public ProductoModel updateProducto(String productoId, CreateProductoDto productoDto) {
		ProductoModel producto = productoRepository.findById(productoId);

		if (producto == null) {
			throw new NotFoundException("Producto no existe");
		}

		boolean isNewProductoNombre = productoDto.nombre != null && !productoDto.nombre.equals("")
				&& productoDto.nombre.equals(producto.getNombre()) == false;

		if (isNewProductoNombre) {
			ProductoModel productoNombreExists = productoRepository.findByNombre(productoDto.nombre);
			
			if (productoNombreExists != null) {
				throw new BadRequestException("Nombre ya existe");
			}
			
			producto.setNombre(productoDto.nombre);
			producto.setUpdatedAt(new Date());
		}
		
		if (productoDto.descripcion != null && !productoDto.descripcion.equals("")) {
			producto.setDescripcion(productoDto.descripcion);
			producto.setUpdatedAt(new Date());
		}
		
		if (productoDto.precio != null) {
			producto.setPrecio(productoDto.precio);
			producto.setUpdatedAt(new Date());
		}

		boolean isNewCodigoBarras = productoDto.codigoBarras != null && !productoDto.codigoBarras.equals("")
				&& productoDto.codigoBarras.equals(producto.getCodigoBarras()) == false;

		if (isNewCodigoBarras) {
			ProductoModel productoCodigoBarrasExists = productoRepository.findByCodigoBarras(productoDto.codigoBarras);

			if (productoCodigoBarrasExists != null) {
				throw new BadRequestException("Codigo de barras ya existe");
			}
			
			producto.setCodigoBarras(productoDto.codigoBarras);
			producto.setUpdatedAt(new Date());
		}

		boolean isNewMarcaId = productoDto.marcaId != null && !productoDto.marcaId.equals("")
				&& productoDto.marcaId.equals(producto.getMarcaId()) == false;

		if (isNewMarcaId) {
			MarcaModel marca = marcaRepository.findById(productoDto.marcaId);

			if (marca == null) {
				throw new BadRequestException("Marca no existe");
			}

			producto.setMarcaId(productoDto.marcaId);
			producto.setMarca(marca);
			producto.setUpdatedAt(new Date());
		}

		boolean isNewCategoriaId = productoDto.categoriaId != null && !productoDto.categoriaId.equals("")
				&& productoDto.categoriaId.equals(producto.getCategoriaId()) == false;

		if (isNewCategoriaId) {
			CategoriaModel categoria = categoriaRepository.findById(productoDto.categoriaId);
			
			if (categoria == null) {
				throw new BadRequestException("Categoria no existe");
			}

			producto.setCategoriaId(productoDto.categoriaId);
			producto.setCategoria(categoria);
			producto.setUpdatedAt(new Date());
		}

		productoRepository.update(producto);

		return producto;
	}

	@Override
	public ProductoModel deleteProducto(String productoId) {
		ProductoModel producto = productoRepository.findById(productoId);

		if (producto == null) {
			throw new NotFoundException("Producto no existe"); 
		}
		
		productoRepository.delete(productoId);
		
		return producto;
	}

}
