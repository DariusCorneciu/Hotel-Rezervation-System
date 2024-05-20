-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema hotelrezervation
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema hotelrezervation
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `hotelrezervation` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `hotelrezervation` ;

-- -----------------------------------------------------
-- Table `hotelrezervation`.`hotels`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hotelrezervation`.`hotels` (
  `idhotels` INT NOT NULL AUTO_INCREMENT,
  `hotelName` VARCHAR(45) NOT NULL,
  `review` DOUBLE NOT NULL,
  PRIMARY KEY (`idhotels`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `hotelrezervation`.`userstype`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hotelrezervation`.`userstype` (
  `idUsersType` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idUsersType`, `Name`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `hotelrezervation`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hotelrezervation`.`users` (
  `idUsers` INT NOT NULL AUTO_INCREMENT,
  `emailAddress` VARCHAR(45) NOT NULL,
  `firstName` VARCHAR(45) NOT NULL,
  `lastName` VARCHAR(45) NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  `usertypeId` INT NOT NULL,
  `salary` INT NULL DEFAULT NULL,
  `hotelId` INT NULL DEFAULT NULL,
  `review` DOUBLE NULL DEFAULT NULL,
  PRIMARY KEY (`idUsers`),
  INDEX `usertype_idx` (`usertypeId` ASC) VISIBLE,
  INDEX `hotels_idx` (`hotelId` ASC) VISIBLE,
  CONSTRAINT `hotels`
    FOREIGN KEY (`hotelId`)
    REFERENCES `hotelrezervation`.`hotels` (`idhotels`),
  CONSTRAINT `typeuser`
    FOREIGN KEY (`usertypeId`)
    REFERENCES `hotelrezervation`.`userstype` (`idUsersType`))
ENGINE = InnoDB
AUTO_INCREMENT = 15
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `hotelrezervation`.`cards`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hotelrezervation`.`cards` (
  `idCards` INT NOT NULL AUTO_INCREMENT,
  `cardNumber` VARCHAR(45) NOT NULL,
  `validThru` DATETIME NOT NULL,
  `placeHolder` VARCHAR(100) NOT NULL,
  `ccv` INT NOT NULL,
  `userId` INT NOT NULL,
  `type` VARCHAR(45) NOT NULL,
  `money` INT NULL DEFAULT '0',
  PRIMARY KEY (`idCards`),
  INDEX `users_idx` (`userId` ASC) VISIBLE,
  CONSTRAINT `users`
    FOREIGN KEY (`userId`)
    REFERENCES `hotelrezervation`.`users` (`idUsers`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `hotelrezervation`.`reservations`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hotelrezervation`.`reservations` (
  `idreservations` INT NOT NULL AUTO_INCREMENT,
  `startDate` DATETIME NOT NULL,
  `endDate` DATETIME NOT NULL,
  `payed` BIT(1) NOT NULL,
  `cost` INT NOT NULL,
  `userId` INT NOT NULL,
  `hotelId` INT NOT NULL,
  PRIMARY KEY (`idreservations`),
  INDEX `userrez_idx` (`userId` ASC) VISIBLE,
  INDEX `hotelrez_idx` (`hotelId` ASC) VISIBLE,
  CONSTRAINT `hotelrez`
    FOREIGN KEY (`hotelId`)
    REFERENCES `hotelrezervation`.`hotels` (`idhotels`),
  CONSTRAINT `userrez`
    FOREIGN KEY (`userId`)
    REFERENCES `hotelrezervation`.`users` (`idUsers`))
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `hotelrezervation`.`rooms`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hotelrezervation`.`rooms` (
  `idrooms` INT NOT NULL AUTO_INCREMENT,
  `bedNumber` INT NOT NULL,
  `view` VARCHAR(100) NOT NULL,
  `price` INT NOT NULL,
  `hotelId` INT NOT NULL,
  `reservationId` INT NULL DEFAULT '-1',
  PRIMARY KEY (`idrooms`),
  INDEX `hotelroom_idx` (`hotelId` ASC) VISIBLE,
  INDEX `roez_idx` (`reservationId` ASC) VISIBLE,
  CONSTRAINT `hotelroom`
    FOREIGN KEY (`hotelId`)
    REFERENCES `hotelrezervation`.`hotels` (`idhotels`),
  CONSTRAINT `roez`
    FOREIGN KEY (`reservationId`)
    REFERENCES `hotelrezervation`.`reservations` (`idreservations`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;



--- Insert ---
INSERT INTO `hotelrezervation`.`userstype` (`Name`) VALUES ("Receptionist");
INSERT INTO `hotelrezervation`.`userstype` (`Name`) VALUES ("Admin");
INSERT INTO `hotelrezervation`.`userstype` (`Name`) VALUES ("User");
SELECT * FROM hotelrezervation.users;
INSERT INTO `hotelrezervation`.`users`
(`emailAddress`,
`firstName`,
`lastName`,
`password`,
`usertypeId`,
`salary`)
VALUES
("admin@gmail.com",
"Admin",
"Test",
"a80b568a237f50391d2f1f97beaf99564e33d2e1c8a2e5cac21ceda701570312",
2,
6000);
