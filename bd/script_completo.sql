-- MySQL Script generated by MySQL Workbench
-- Tue May 29 19:10:12 2018
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema silalibroDB
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema silalibroDB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `silalibroDB` DEFAULT CHARACTER SET utf8 ;
USE `silalibroDB` ;

-- -----------------------------------------------------
-- Table `silalibroDB`.`direccion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `silalibroDB`.`direccion` (
  `iddireccion` INT NOT NULL AUTO_INCREMENT,
  `direccion_calle` VARCHAR(45) NULL,
  `direccion_numeroExterior` INT NULL,
  `direccion_numeroInterior` INT NULL,
  `direccion_cp` VARCHAR(5) NULL,
  `direccion_colonia` VARCHAR(45) NULL,
  `direccion_municipio` VARCHAR(45) NULL,
  `direccion_estado` VARCHAR(45) NULL,
  PRIMARY KEY (`iddireccion`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `silalibroDB`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `silalibroDB`.`usuario` (
  `idusuario` INT NOT NULL AUTO_INCREMENT,
  `usuario_email` VARCHAR(45) NULL,
  `usuario_pass` TEXT NULL,
  `usuario_nombre` VARCHAR(45) NULL,
  `usuario_apellidoMaterno` VARCHAR(45) NULL,
  `usuario_apellidoPaterno` VARCHAR(45) NULL,
  `usuario_fechaNacimiento` DATE NULL,
  `idDireccion` INT NULL,
  `usuario_administrador` BIT NULL,
  PRIMARY KEY (`idusuario`),
  INDEX `usuario_direccion_idx` (`idDireccion` ASC),
  CONSTRAINT `usuario_direccion`
    FOREIGN KEY (`idDireccion`)
    REFERENCES `silalibroDB`.`direccion` (`iddireccion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `silalibroDB`.`movimiento_cuenta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `silalibroDB`.`movimiento_cuenta` (
  `idmovimiento_cuenta` INT NOT NULL AUTO_INCREMENT,
  `movimiento_cuenta_monto` DECIMAL NULL,
  `movimiento_cuenta_cargo` BIT NULL,
  `movimiento_cuenta_fecha` DATE NULL,
  `movimiento_cuenta_folioTransaccion` TEXT NULL,
  `movimiento_cuenta_idusuario` INT NULL,
  PRIMARY KEY (`idmovimiento_cuenta`),
  INDEX `movimiento_cuenta_usuario_idx` (`movimiento_cuenta_idusuario` ASC),
  CONSTRAINT `movimiento_cuenta_usuario`
    FOREIGN KEY (`movimiento_cuenta_idusuario`)
    REFERENCES `silalibroDB`.`usuario` (`idusuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `silalibroDB`.`pais`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `silalibroDB`.`pais` (
  `idpais` INT NOT NULL AUTO_INCREMENT,
  `pais_nombre` VARCHAR(45) NULL,
  PRIMARY KEY (`idpais`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `silalibroDB`.`autor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `silalibroDB`.`autor` (
  `idautor` INT NOT NULL AUTO_INCREMENT,
  `autor_nombre` VARCHAR(45) NULL,
  `autor_apellidoPaterno` VARCHAR(45) NULL,
  `autor_apellidoMaterno` VARCHAR(45) NULL,
  `autor_idpais` INT NULL,
  PRIMARY KEY (`idautor`),
  INDEX `autor_pais_idx` (`autor_idpais` ASC),
  CONSTRAINT `autor_pais`
    FOREIGN KEY (`autor_idpais`)
    REFERENCES `silalibroDB`.`pais` (`idpais`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `silalibroDB`.`libro`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `silalibroDB`.`libro` (
  `idlibro` INT NOT NULL AUTO_INCREMENT,
  `libro_sku` TEXT NULL,
  `libro_titulo` VARCHAR(45) NULL,
  `libro_idautor` INT NULL,
  `librocol` VARCHAR(45) NULL,
  PRIMARY KEY (`idlibro`),
  INDEX `libro_autor_idx` (`libro_idautor` ASC),
  CONSTRAINT `libro_autor`
    FOREIGN KEY (`libro_idautor`)
    REFERENCES `silalibroDB`.`autor` (`idautor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `silalibroDB`.`renta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `silalibroDB`.`renta` (
  `idrenta` INT NOT NULL AUTO_INCREMENT,
  `renta_idusuario` INT NULL,
  `renta_fechaRegistro` DATETIME NULL,
  `renta_movimientoCuentaid` INT NULL,
  `renta_montoTotal` DECIMAL NULL,
  PRIMARY KEY (`idrenta`),
  INDEX `renta_usuario_fk_idx` (`renta_idusuario` ASC),
  INDEX `renta_movimiento_fk_idx` (`renta_movimientoCuentaid` ASC),
  CONSTRAINT `renta_usuario_fk`
    FOREIGN KEY (`renta_idusuario`)
    REFERENCES `silalibroDB`.`usuario` (`idusuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `renta_movimiento_fk`
    FOREIGN KEY (`renta_movimientoCuentaid`)
    REFERENCES `silalibroDB`.`movimiento_cuenta` (`idmovimiento_cuenta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `silalibroDB`.`venta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `silalibroDB`.`venta` (
  `idventa` INT NOT NULL AUTO_INCREMENT,
  `venta_idusuario` INT NULL,
  `venta_montoTotal` DECIMAL NULL,
  `venta_movimientoCuentaid` INT NULL,
  `venta_fechaRegistro` DATETIME NULL,
  PRIMARY KEY (`idventa`),
  INDEX `venta_usuario_fk_idx` (`venta_idusuario` ASC),
  INDEX `venta_movimiento_fk_idx` (`venta_movimientoCuentaid` ASC),
  CONSTRAINT `venta_usuario_fk`
    FOREIGN KEY (`venta_idusuario`)
    REFERENCES `silalibroDB`.`usuario` (`idusuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `venta_movimiento_fk`
    FOREIGN KEY (`venta_movimientoCuentaid`)
    REFERENCES `silalibroDB`.`movimiento_cuenta` (`idmovimiento_cuenta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `silalibroDB`.`almacen`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `silalibroDB`.`almacen` (
  `idalmacen` INT NOT NULL AUTO_INCREMENT,
  `almacen_idlibro` INT NULL,
  `almacen_existencias` INT NULL,
  `almacen_fechaEdicionAlmacen` DATETIME NULL,
  `almacen_ideditadoPor` INT NULL,
  PRIMARY KEY (`idalmacen`),
  INDEX `almacen_administrador_idx` (`almacen_ideditadoPor` ASC),
  INDEX `almacen_libro_fk_idx` (`almacen_idlibro` ASC),
  CONSTRAINT `almacen_administrador`
    FOREIGN KEY (`almacen_ideditadoPor`)
    REFERENCES `silalibroDB`.`usuario` (`idusuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `almacen_libro_fk`
    FOREIGN KEY (`almacen_idlibro`)
    REFERENCES `silalibroDB`.`libro` (`idlibro`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `silalibroDB`.`rentaventa_libro`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `silalibroDB`.`rentaventa_libro` (
  `idrentaventa_libro` INT NOT NULL AUTO_INCREMENT,
  `rentaventa_libro_idlibro` INT NULL,
  `rentaventa_libro_idrentaventa` INT NULL,
  `rentaventa_libro_monto` DECIMAL NULL,
  PRIMARY KEY (`idrentaventa_libro`),
  INDEX `rentaventa_libro_fk_idx` (`rentaventa_libro_idlibro` ASC),
  INDEX `rentaventa_renta_fk_idx` (`rentaventa_libro_idrentaventa` ASC),
  CONSTRAINT `rentaventa_libro_fk`
    FOREIGN KEY (`rentaventa_libro_idlibro`)
    REFERENCES `silalibroDB`.`libro` (`idlibro`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `rentaventa_renta_fk`
    FOREIGN KEY (`rentaventa_libro_idrentaventa`)
    REFERENCES `silalibroDB`.`renta` (`idrenta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `rentaventa_venta_fk`
    FOREIGN KEY (`rentaventa_libro_idrentaventa`)
    REFERENCES `silalibroDB`.`venta` (`idventa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
