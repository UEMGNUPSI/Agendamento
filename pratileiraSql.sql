CREATE SCHEMA IF NOT EXISTS `pratileira` DEFAULT CHARACTER SET utf8 ;
USE `pratileira` ;


-- -----------------------------------------------------
-- Table `mydb`.`funcionario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pratileira`.`funcionario` (
  `idfuncionario` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL,
  `rg` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL,
  `cpf` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL,
  `nascimento` DATE NULL,
  `telefone1` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL,
  `telefone2` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL,
  `endereco` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL,
  `cidade` VARCHAR(50) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL,
  `cep` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL,
  `bairro` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL,
  `numero` VARCHAR(10) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL,
  `email` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL,
  `login` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL,
  `senha` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL,
  `nivel` TINYINT NULL,
  PRIMARY KEY (`idfuncionario`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pratileira`.`cliente` (
  `idcliente` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL,
  `rg` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL,
  `cpf` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL,
  `nascimento` DATE NULL,
  `telefone1` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL,
  `telefone2` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL,
  `email` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL,
  `descricao` VARCHAR(200) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL,
  `endereco` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL,
  `numero` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL,
  `bairro` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL,
  `cidade` VARCHAR(50) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL,
  `cep` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL,
  PRIMARY KEY (`idcliente`))
ENGINE = InnoDB;

-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pratileira`.`Area` (
  `idarea` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NULL,
  PRIMARY KEY (`idarea`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `mydb`.`serviços`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pratileira`.`serviços` (
  `idserviços` INT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(80) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL,
  `tempo` TIME NOT NULL,
  `idarea` INT NOT NULL,
  PRIMARY KEY (`idserviços`),
  INDEX `fk_serviços_Area1_idx` (`idarea` ASC),
  CONSTRAINT `fk_serviços_Area1`
    FOREIGN KEY (`idarea`)
    REFERENCES `pratileira`.`Area` (`idarea`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `mydb`.`profissionais`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pratileira`.`profissionais` (
  `idprofissionais` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL,
  `rg` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL,
  `cpf` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL,
  `nascimento` DATE NULL,
  `telefone1` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL,
  `telefone2` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL,
  `email` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL,
  `endereco` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL,
  `cidade` VARCHAR(50) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL,
  `cep` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL,
  `bairro` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL,
  `numero` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL,
  `formacao` VARCHAR(75) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL,
  `idarea` INT NOT NULL,
  PRIMARY KEY (`idprofissionais`),
  INDEX `fk_profissionais_Area1_idx` (`idarea` ASC),
  CONSTRAINT `fk_profissionais_Area1`
    FOREIGN KEY (`idarea`)
    REFERENCES `pratileira`.`Area` (`idarea`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`agendamento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pratileira`.`agendamento` (
  `idagendamento` INT NOT NULL AUTO_INCREMENT,
  `idfuncionario` INT NOT NULL,
  `idcliente` INT NOT NULL,
  `idprofissionais` INT NOT NULL,
  `idserviços` INT NOT NULL,
  `dataAgendamento` DATE NULL,
  `horarioAgendamento` TIME NULL,
  `horarioPrevistoTermino` TIME NULL,
  `dataCancelamento` DATE NULL,
  `dataAtendimento` DATE NULL,
  `horarioAtendimento` TIME NULL,
  PRIMARY KEY (`idagendamento`),
  INDEX `fk_agendamento_funcionario_idx` (`idfuncionario` ASC),
  INDEX `fk_agendamento_cliente1_idx` (`idcliente` ASC),
  INDEX `fk_agendamento_profissionais1_idx` (`idprofissionais` ASC),
  INDEX `fk_agendamento_serviços1_idx` (`idserviços` ASC),
  CONSTRAINT `fk_agendamento_funcionario`
    FOREIGN KEY (`idfuncionario`)
    REFERENCES `pratileira`.`funcionario` (`idfuncionario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_agendamento_cliente1`
    FOREIGN KEY (`idcliente`)
    REFERENCES `pratileira`.`cliente` (`idcliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_agendamento_profissionais1`
    FOREIGN KEY (`idprofissionais`)
    REFERENCES `pratileira`.`profissionais` (`idprofissionais`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_agendamento_serviços1`
    FOREIGN KEY (`idserviços`)
    REFERENCES `pratileira`.`serviços` (`idserviços`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;