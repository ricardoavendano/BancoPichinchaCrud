 /*-------------------------------------------------------------------------------------------*/
DROP TABLE IF EXISTS persona;
CREATE TABLE persona (
  idpersona INT NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(100) NOT NULL,
  genero VARCHAR(100) NOT NULL,
  edad INT NOT NULL,
  identificacion INT NOT NULL,
  direccion VARCHAR(100) NOT NULL,
  telefono INT NOT NULL,
  PRIMARY KEY (idpersona));

INSERT INTO persona (nombre, genero, edad, identificacion, direccion, telefono) VALUES
('Jose Lema', 'Masculino', 30, 123456, 'direccion 1', 11111111),
('Marianela Montalvo', 'Femenino', 32, 234567, 'direccion 2', 2222222),
('Juan Osorio', 'Masculino', 33, 345678, 'direccion 3', 33333333);

/*-------------------------------------------------------------------------------------------*/

DROP TABLE IF EXISTS cliente;
CREATE TABLE cliente (
  idcliente INT NOT NULL AUTO_INCREMENT,
  idpersonaPK INT UNIQUE NOT NULL,
  contrasena VARCHAR(100) NOT NULL,
  estado BOOLEAN NOT NULL,
  PRIMARY KEY (idcliente));

ALTER TABLE cliente 
ADD CONSTRAINT idpersonaPK
  FOREIGN KEY (idpersonaPK)
  REFERENCES persona (idpersona);

INSERT INTO cliente (idpersonaPK, contrasena, estado) VALUES 
(1, 'Y29udHJhc2VuYTE=', true),
(2, 'Y29udHJhc2VuYTI=', false),
(3, 'Y29udHJhc2VuYTM=', true);
/*-------------------------------------------------------------------------------------------*/

DROP TABLE IF EXISTS cuenta;
CREATE TABLE cuenta (
  idcuenta INT NOT NULL AUTO_INCREMENT,
  idclientePK INT NOT NULL,
  numerocuenta INT NOT NULL,
  tipocuenta VARCHAR(100) NOT NULL,
  saldoinicial INT NOT NULL,
  estado BOOLEAN NOT NULL,
  PRIMARY KEY (idcuenta));

ALTER TABLE cuenta 
ADD CONSTRAINT idclientePK
  FOREIGN KEY (idclientePK)
  REFERENCES cliente (idcliente);

INSERT INTO cuenta (idclientePK, numerocuenta, tipocuenta, saldoinicial, estado) VALUES 
(1, 999999, 'Ahorros', 100, true),
(1, 888888, 'Corriente', 100, false),
(2, 777777, 'Ahorros', 200, true),
(3, 666666, 'Ahorros', 300, true),
(3, 555555, 'Corriente', 600, true);
/*-------------------------------------------------------------------------------------------*/

DROP TABLE IF EXISTS movimientos;
CREATE TABLE movimientos (
  idmovimiento INT NOT NULL AUTO_INCREMENT,
  idcuentaPK INT NOT NULL,
  fecha DATE NOT NULL,
  tipomovimiento VARCHAR(100) NOT NULL,
  valor INT NOT NULL,
  saldo INT NOT NULL,
  PRIMARY KEY (idmovimiento));

ALTER TABLE movimientos 
ADD CONSTRAINT idcuentaPK
  FOREIGN KEY (idcuentaPK)
  REFERENCES cuenta (idcuenta);

INSERT INTO movimientos (idcuentaPK, fecha, tipomovimiento, valor, saldo) VALUES 
(1, '2023-03-01', 'Deposito', 100, 200),
(1, '2023-03-02', 'Retiro', -50, 150),
(1, '2023-03-03', 'Retiro', -70, 80),
(2, '2023-03-01', 'Deposito', 70, 170),
(3, '2023-03-02', 'Deposito', 40, 240),
(3, '2023-03-03', 'Deposito', 50, 290),
(4, '2023-03-04', 'Retiro', -50, 250),
(5, '2023-03-05', 'Retiro', -200, 400);
/*-------------------------------------------------------------------------------------------*/