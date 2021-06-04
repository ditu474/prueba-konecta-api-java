CREATE DATABASE IF NOT EXISTS prueba_konecta;
USE prueba_konecta;

-- tablas
CREATE TABLE IF NOT EXISTS empleado (
	id INT NOT NULL AUTO_INCREMENT,
    fecha_ingreso DATETIME DEFAULT CURRENT_TIMESTAMP,
    nombre VARCHAR(50) NOT NULL,
    salario FLOAT DEFAULT 0,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS solicitud (
	id INT NOT NULL AUTO_INCREMENT,
    codigo VARCHAR(50) NOT NULL UNIQUE,
    descripcion VARCHAR(50) NOT NULL,
    resumen VARCHAR(50) NOT NULL,
    id_empleado INT NOT NULL,
    FOREIGN KEY (id_empleado) REFERENCES empleado(id),
    PRIMARY KEY (id)
);