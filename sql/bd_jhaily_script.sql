DROP DATABASE IF EXISTS bdsistemajhayli;

CREATE DATABASE bdsistemajhayli;

USE bdsistemajhayli;

CREATE TABLE IF NOT EXISTS moneda(
    id CHAR(36) NOT NULL,
    
    nombre VARCHAR(30),
    codigo VARCHAR(10),
    
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS pais(
    id VARCHAR(2) NOT NULL,
    
    nombre VARCHAR(30),

	PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS region(
    id VARCHAR(2) NOT NULL,
	pais_id VARCHAR(2),

    nombre VARCHAR(45),
    
	PRIMARY KEY (id),
    FOREIGN KEY (pais_id) REFERENCES pais(id)
);

CREATE TABLE IF NOT EXISTS provincia(
    id varchar(4) NOT NULL,
    region_id VARCHAR(2),

    nombre VARCHAR(45),
    
	PRIMARY KEY (id),
    FOREIGN KEY (region_id) REFERENCES region(id)
);

CREATE TABLE IF NOT EXISTS distrito(
    id VARCHAR(6) NOT NULL,
    provincia_id VARCHAR(4),
    region_id VARCHAR(2),

    nombre VARCHAR(45),
    
	PRIMARY KEY (id),
    FOREIGN KEY (provincia_id) REFERENCES provincia(id),
    FOREIGN KEY (region_id) REFERENCES region(id)
);

CREATE TABLE IF NOT EXISTS ubicacion(
    id CHAR(36) NOT NULL,
    distrito_id VARCHAR(6),

    direccion VARCHAR(50) NOT NULL,

	PRIMARY KEY (id),
    FOREIGN KEY (distrito_id) REFERENCES distrito(id)
);

CREATE TABLE IF NOT EXISTS categoria(
    id CHAR(36) NOT NULL,

    nombre VARCHAR(30) NOT NULL,
    descripcion VARCHAR(255),
    
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	deleted_at TIMESTAMP NULL DEFAULT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS marca(
	id CHAR(36) NOT NULL,
	categoria_id CHAR(36),
    
    nombre NVARCHAR(30) NOT NULL,
	descripcion VARCHAR(255),
    
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	deleted_at TIMESTAMP NULL DEFAULT NULL,

    PRIMARY KEY (id),
	FOREIGN KEY (categoria_id) REFERENCES categoria(id)
);

CREATE TABLE IF NOT EXISTS producto(
    id CHAR(36) NOT NULL,
	marca_id CHAR(36) NOT NULL,
    categoria_id CHAR(36) NOT NULL,

    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(255) NOT NULL,
    precio DECIMAL(8, 2) NOT NULL,
	codigo_barras VARCHAR(13) NOT NULL,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	deleted_at TIMESTAMP NULL DEFAULT NULL,

	PRIMARY KEY (id),
    FOREIGN KEY (categoria_id) REFERENCES categoria(id),
    FOREIGN KEY (marca_id) REFERENCES marca(id)
);

CREATE TABLE IF NOT EXISTS empresa(
	id CHAR(36) NOT NULL,

	ruc NVARCHAR(17) NOT NULL,
    razon_social NVARCHAR(50) NOT NULL,
    
	fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	fecha_eliminacion TIMESTAMP NULL DEFAULT NULL,
    
	PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS persona(
	id CHAR(36) NOT NULL,
    
	nombres VARCHAR(40) NOT NULL,
	apellidos VARCHAR(40) NOT NULL,
    fecha_nacimiento DATE,
    sexo CHAR(1),
    
	fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	fecha_eliminacion TIMESTAMP NULL DEFAULT NULL,

	PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS rol(
	id CHAR(36) NOT NULL,
    
	nombre VARCHAR(40) NOT NULL,
    
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	deleted_at TIMESTAMP NULL DEFAULT NULL,
    
	PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS modulo(
	id CHAR(36) NOT NULL,
    
    nombre VARCHAR(30),
    
	PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS operacion(
	id CHAR(36) NOT NULL,
    modulo_id CHAR(36),

    nombre VARCHAR(60),      

    PRIMARY KEY (id),
    FOREIGN KEY (modulo_id) REFERENCES modulo(id)
);

CREATE TABLE IF NOT EXISTS rol_operacion(
	id  CHAR(36) NOT NULL,
	rol_id CHAR(36),
    operacion_id  CHAR(36),    
    
    PRIMARY KEY (id),
    FOREIGN KEY (rol_id) REFERENCES rol(id),
    FOREIGN KEY (operacion_id) REFERENCES operacion(id)
);

CREATE TABLE IF NOT EXISTS empleado(
	id CHAR(36) NOT NULL,
    persona_id CHAR(36),
	rol_id CHAR(36),
    
    email  VARCHAR(36) NOT NULL,
    username VARCHAR(45) NOT NULL,
    password VARCHAR(60),
    
    PRIMARY KEY (id),
	FOREIGN KEY (persona_id) REFERENCES persona(id),
	FOREIGN KEY (rol_id) REFERENCES rol(id)
);


CREATE TABLE IF NOT EXISTS proveedor(
	id CHAR(36) NOT NULL,

	nombre VARCHAR(80) NOT NULL,
	email VARCHAR(36) NOT NULL,
	direccion VARCHAR(255) NOT NULL,
    telefono VARCHAR(10) NOT NULL,
    
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	deleted_at TIMESTAMP NULL DEFAULT NULL,
    
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS usuario(
	id CHAR(36) NOT NULL,
	rol_id CHAR(36) NOT NULL,
    
	nombre VARCHAR(40) NOT NULL,
	apellido_paterno VARCHAR(40) NOT NULL,
	apellido_materno VARCHAR(40) NOT NULL,
    email  VARCHAR(36) NOT NULL,
    username VARCHAR(36) NOT NULL,
    password VARCHAR(60) NOT NULL,
    
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	deleted_at TIMESTAMP NULL DEFAULT NULL,

	PRIMARY KEY (id),
	FOREIGN KEY (rol_id) REFERENCES rol(id)
);

CREATE TABLE IF NOT EXISTS recepcion(
    id CHAR(36) NOT NULL,
    usuario_id CHAR(36) NOT NULL,
    proveedor_id CHAR(36) NOT NULL,
    
    estado INT DEFAULT 0,

	recibido_at TIMESTAMP NULL DEFAULT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	deleted_at TIMESTAMP NULL DEFAULT NULL,

	PRIMARY KEY (id),
    FOREIGN KEY (usuario_id) REFERENCES usuario(id),
    FOREIGN KEY (proveedor_id) REFERENCES proveedor(id)
);

CREATE TABLE IF NOT EXISTS recepcion_detalle(
    id CHAR(36) NOT NULL,
    recepcion_id CHAR(36) NOT NULL,
    producto_id CHAR(36) NOT NULL,
    
    precio DECIMAL(8, 2) NOT NULL,
    cantidad INT NOT NULL,
    
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	deleted_at TIMESTAMP NULL DEFAULT NULL,
    
	PRIMARY KEY (id),
	FOREIGN KEY (recepcion_id) REFERENCES recepcion(id),
    FOREIGN KEY (producto_id) REFERENCES producto(id)
);
