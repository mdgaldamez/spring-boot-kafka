CREATE DATABASE IF NOT EXISTS test;
USE test;

CREATE TABLE IF NOT EXISTS usuario (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    genero VARCHAR(10),
    fecha_nacimiento DATE,
    identificacion VARCHAR(20),
    direccion VARCHAR(255),
    telefono VARCHAR(20),
	contrasena VARCHAR(255) NOT NULL,
    estado VARCHAR(20),
    cliente_id INT(11) NOT NULL UNIQUE,
    dtype VARCHAR(80) NULL DEFAULT 'Cliente'
);

CREATE TABLE IF NOT EXISTS cuenta (
    id INT PRIMARY KEY AUTO_INCREMENT,
    numero VARCHAR(20) UNIQUE,
    tipo VARCHAR(50),
    saldo_inicial DECIMAL(10, 2),
    estado VARCHAR(20),
    usuario_id INT(11), 
    FOREIGN KEY (usuario_id) REFERENCES usuario (id)
);

CREATE TABLE IF NOT EXISTS movimiento (
    id INT PRIMARY KEY AUTO_INCREMENT,
    fecha DATETIME DEFAULT CURRENT_TIMESTAMP,
    tipo ENUM('Retiro', 'Deposito') NULL DEFAULT NULL,
    valor DECIMAL(10, 2),
    saldo DECIMAL(10, 2),
    cuenta_id INT(11),
    FOREIGN KEY (cuenta_id) REFERENCES cuenta (id)
);

INSERT INTO `test`.`usuario` (`nombre`, `genero`, `fecha_nacimiento`, `identificacion`, `direccion`, `telefono`, `contrasena`, `estado`, `cliente_id`, `dtype`)
VALUES ('Jose Lema', 'Masculino', '1989-02-10', 'DNI', 'Otavalo sn y principal', '098254785', '1234', 'True', '123456789', 'Cliente');
INSERT INTO `test`.`usuario` (`nombre`, `genero`, `fecha_nacimiento`, `identificacion`, `direccion`, `telefono`, `contrasena`, `estado`, `cliente_id`, `dtype`)
VALUES ('Marianela Montalvo', 'Femenino', '1993-07-14', 'DNI', 'Amazonas y NNUU', '097548965', '5678', 'True', '234567890', 'Cliente');
INSERT INTO `test`.`usuario` (`nombre`, `genero`, `fecha_nacimiento`, `identificacion`, `direccion`, `telefono`, `contrasena`, `estado`, `cliente_id`, `dtype`)
VALUES ('Juan Osorio', 'Masculino', '1990-05-05', 'DNI', '13 junio y Equinoccial', '098874587', '1245', 'True', '345678901', 'Cliente');

INSERT INTO `test`.`cuenta` (`numero`, `tipo`, `saldo_inicial`, `estado`, `usuario_id`) VALUES ('478758', 'Ahorros', '2000', 'True', '1');
INSERT INTO `test`.`cuenta` (`numero`, `tipo`, `saldo_inicial`, `estado`, `usuario_id`) VALUES ('225487', 'Corriente', '100', 'True', '2');
INSERT INTO `test`.`cuenta` (`numero`, `tipo`, `saldo_inicial`, `estado`, `usuario_id`) VALUES ('495878', 'Ahorros', '0', 'True', '3');
INSERT INTO `test`.`cuenta` (`numero`, `tipo`, `saldo_inicial`, `estado`, `usuario_id`) VALUES ('496825', 'Ahorros', '540', 'True', '2');

INSERT INTO `test`.`movimiento`(`fecha`, `tipo`, `valor`, `saldo`, `cuenta_id`) VALUES (now(), 'Retiro', 575, 2000, 1);