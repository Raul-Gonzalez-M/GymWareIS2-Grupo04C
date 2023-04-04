CREATE TABLE actividad (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    fecha_inicio DATETIME NOT NULL,
    fecha_fin DATETIME NOT NULL,
    descripcion TEXT
);

CREATE TABLE aula (
    id INT PRIMARY KEY AUTO_INCREMENT,
    capacidad_maxima INT NOT NULL,
    capacidad_actual INT NOT NULL,
    nombre VARCHAR(255) NOT NULL
);

CREATE TABLE usuario (
    dni VARCHAR(9) PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    apellido VARCHAR(255) NOT NULL,
    edad INT NOT NULL,
    correo_electronico VARCHAR(255) NOT NULL,
    contrasena VARCHAR(255) NOT NULL
);

CREATE TABLE cliente (
    id INT NOT NULL AUTO_INCREMENT,
	dni VARCHAR(9) PRIMARY KEY NOT NULL,
    peso DOUBLE,
    altura DOUBLE,
    saldo DOUBLE,
	FOREIGN KEY (DNI) REFERENCES Usuario(DNI)
);

CREATE TABLE personal (
  idPersonal INT NOT NULL AUTO_INCREMENT,
  dni VARCHAR(9) PRIMARY KEY NOT NULL,
  puesto VARCHAR(50) NOT NULL,
  PRIMARY KEY (idPersonal),
  FOREIGN KEY (DNI) REFERENCES usuario(DNI)
);

CREATE TABLE encuesta (
  idEncuesta INT NOT NULL AUTO_INCREMENT,
  idUsuario INT NOT NULL,
  respuesta VARCHAR(255) NOT NULL,
  PRIMARY KEY (idEncuesta),
  FOREIGN KEY (idUsuario) REFERENCES cliente(id)
);

CREATE TABLE material (
  nombre VARCHAR(50) PRIMARY KEY,
  precio DOUBLE,
  unidades INT
);

CREATE TABLE detalles_venta (
    id_detalle INT PRIMARY KEY AUTO_INCREMENT,
    id_venta INT NOT NULL,
    id_material INT NOT NULL,
    cantidad INT NOT NULL,
    FOREIGN KEY (id_venta) REFERENCES venta(id_venta),
    FOREIGN KEY (id_material) REFERENCES material(id_material)
);
