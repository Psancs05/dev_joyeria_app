-- -----------------------------------------------------
-- Schema bdJoyeria
-- -----------------------------------------------------
-- Base de datos de la Joyeria Claudio para el trabajo de ingenieria del software
CREATE SCHEMA IF NOT EXISTS `bdJoyeria` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;

USE `bdJoyeria`;

-- -----------------------------------------------------
-- Table `bdJoyeria`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdJoyeria`.`usuario` (
  `DNI` VARCHAR(9) NOT NULL,
  `Nombre` VARCHAR(45) NOT NULL,
  `Email` VARCHAR(45) NOT NULL,
  `Password` VARCHAR(45) NOT NULL,
  `TipoUsuario` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`DNI`)
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `bdJoyeria`.`venta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdJoyeria`.`venta` (
  `IDventa` INT NOT NULL AUTO_INCREMENT,
  `Fecha` DATE NULL,
  `CantidadArticulos` INT NULL,
  `PrecioVenta` DOUBLE NULL,
  `DireccionFacturacion` VARCHAR(50) NULL,
  `DNIUsuario` VARCHAR(45) NULL,
  PRIMARY KEY (`IDventa`),
  UNIQUE INDEX `IDventa_UNIQUE` (`IDventa` ASC) VISIBLE,
  INDEX `DNIUsuario_idx` (`DNIUsuario` ASC) VISIBLE,
  CONSTRAINT `DNIUsuario` FOREIGN KEY (`DNIUsuario`) REFERENCES `bdJoyeria`.`usuario` (`DNI`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `bdJoyeria`.`proveedor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdJoyeria`.`proveedor` (
  `CIF` VARCHAR(10) NOT NULL,
  `Nombre` VARCHAR(45) NULL,
  PRIMARY KEY (`CIF`)
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `bdJoyeria`.`producto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdJoyeria`.`producto` (
  `IDProducto` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(45) NULL,
  `NumeroCuaderno` INT NULL,
  `TipoProducto` VARCHAR(45) NULL,
  `Precio` DOUBLE NULL,
  `Imagen` LONGBLOB NULL,
  `Material` VARCHAR(45) NULL,
  `Proveedor` VARCHAR(45) NULL,
  `Descripcion` VARCHAR(300) NULL,
  `IDVenta` INT NULL,
  PRIMARY KEY (`IDproducto`),
  INDEX `Proveedor_idx` (`Proveedor` ASC) VISIBLE,
  INDEX `FKIDVenta_idx` (`IDVenta` ASC) VISIBLE,
  CONSTRAINT `FKProveedor` FOREIGN KEY (`Proveedor`) REFERENCES `bdJoyeria`.`proveedor` (`CIF`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FKIDVenta` FOREIGN KEY (`IDVenta`) REFERENCES `bdJoyeria`.`venta` (`IDventa`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Ejemplos de usuarios
-- -----------------------------------------------------
INSERT INTO
  usuario (DNI, Nombre, Email, Password, TipoUsuario)
VALUES
  (
    '12345678C',
    'Claudio',
    'cfern00@estudiantes.unileon.es',
    'ClaudioJoyas',
    'ADMINISTRADOR'
  );

INSERT INTO
  usuario (DNI, Nombre, Email, Password, TipoUsuario)
VALUES
  (
    'a',
    'Javier',
    'psancs00@estudiantes.unileon.es',
    'JavierJoyas',
    'CAJERO'
  );

-- -----------------------------------------------------
-- Ejemplos de proveedores
-- -----------------------------------------------------
INSERT INTO
  proveedor (CIF, nombre)
VALUES
  ('6523D', 'proveedor1');

INSERT INTO
  proveedor (CIF, nombre)
VALUES
  ('9876C', 'proveedor2');

INSERT INTO
  proveedor (CIF, nombre)
VALUES
  ('1234J', 'proveedor3');

INSERT INTO
  proveedor (CIF, nombre)
VALUES
  ('7895N', 'proveedor4');