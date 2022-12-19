package com.jhayli.system.services.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jhayli.system.dtos.CreateRecepcionDetalleDto;
import com.jhayli.system.exceptions.BadRequestException;
import com.jhayli.system.exceptions.NotFoundException;
import com.jhayli.system.models.ProductoModel;
import com.jhayli.system.models.RecepcionDetalleModel;
import com.jhayli.system.repositories.ProductoRepository;
import com.jhayli.system.repositories.RecepcionDetalleRepository;
import com.jhayli.system.services.RecepcionDetalleService;

@Service
public class RecepcionDetalleServiceImpl implements RecepcionDetalleService {
	
	@Autowired
	RecepcionDetalleRepository recepcionDetalleRepository;
	
	@Autowired
    private ProductoRepository productoRepository;
	
	@Override
	public List<RecepcionDetalleModel> getRecepcionDetalles(String recepcionId, int limit, int offset) {
		return recepcionDetalleRepository.findAll(recepcionId, limit, offset);
	}

	@Override
	public RecepcionDetalleModel createRecepcionDetalle(String recepcionId, CreateRecepcionDetalleDto recepcionDetalleDto) {
		ProductoModel producto = productoRepository.findById(recepcionDetalleDto.productoId);
		
		if (producto == null) {
			throw new BadRequestException("Producto no existe");
		}
		
		RecepcionDetalleModel recepcionDetalle = new RecepcionDetalleModel();
		recepcionDetalle.setId(UUID.randomUUID().toString());
		recepcionDetalle.setRecepcionId(recepcionId);
		recepcionDetalle.setProductoId(recepcionDetalleDto.productoId);
		recepcionDetalle.setProducto(producto.simple());
		recepcionDetalle.setPrecio(recepcionDetalleDto.precio);
		recepcionDetalle.setCantidad(recepcionDetalleDto.cantidad);
		recepcionDetalle.setCreatedAt(new Date());
		recepcionDetalle.setUpdatedAt(new Date());

		recepcionDetalleRepository.save(recepcionDetalle);

		return recepcionDetalle;
	}

	@Override
	public RecepcionDetalleModel getRecepcionDetalle(String recepcionDetalleId) {
		RecepcionDetalleModel recepcionDetalle = recepcionDetalleRepository.findById(recepcionDetalleId);

		if (recepcionDetalle == null) {
			throw new NotFoundException("Recepcion detalle no existe"); 
		}
		
		return recepcionDetalle;
	}

	@Override
	public RecepcionDetalleModel updateRecepcionDetalle(String recepcionDetalleId,
			CreateRecepcionDetalleDto recepcionDetalleDto) {
		RecepcionDetalleModel recepcionDetalle = recepcionDetalleRepository.findById(recepcionDetalleId);

		if (recepcionDetalle == null) {
			throw new NotFoundException("Recepcion detalle no existe"); 
		}
		

		if (recepcionDetalleDto.productoId != null && !recepcionDetalleDto.productoId.equals("")) {
			ProductoModel producto = productoRepository.findById(recepcionDetalleDto.productoId);
			
			if (producto == null) {
				throw new BadRequestException("Producto no existe");
			}
			recepcionDetalle.setProductoId(recepcionDetalleDto.productoId);
			recepcionDetalle.setProducto(producto.simple());
			recepcionDetalle.setUpdatedAt(new Date());
		}

		if (recepcionDetalleDto.precio != null) {
			recepcionDetalle.setPrecio(recepcionDetalleDto.precio);
			recepcionDetalle.setUpdatedAt(new Date());
		}

		if (recepcionDetalleDto.cantidad != null) {
			recepcionDetalle.setCantidad(recepcionDetalleDto.cantidad);
			recepcionDetalle.setUpdatedAt(new Date());
		}

		
		recepcionDetalleRepository.update(recepcionDetalle);

		return recepcionDetalle;
	}

	@Override
	public RecepcionDetalleModel deleteRecepcionDetalle(String recepcionDetalleId) {
		RecepcionDetalleModel recepcionDetalle = recepcionDetalleRepository.findById(recepcionDetalleId);

		if (recepcionDetalle == null) {
			throw new NotFoundException("Recepcion detalle no existe"); 
		}
		
		recepcionDetalleRepository.delete(recepcionDetalleId);
		
		return recepcionDetalle;
	}

}
