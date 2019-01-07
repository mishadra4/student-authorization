-- -----------------------------------------------------
-- Schema student_authorization
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema student_authorization
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `student_authorization` DEFAULT CHARACTER SET utf8 ;
USE `student_authorization` ;

-- -----------------------------------------------------
-- Table `student_authorization`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `student_authorization`.`user` (
  `student_id` INT NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `enabled` TINYINT NULL,
  PRIMARY KEY (`username`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `student_authorization`.`lecturer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `student_authorization`.`lecturer` (
  `lecturer_id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`lecturer_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `student_authorization`.`lecture`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `student_authorization`.`lecture` (
  `lecture_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `description` VARCHAR(255) NULL,
  `ordinal_number` INT NULL,
  `lecturer_lecturer_id` INT NOT NULL,
  PRIMARY KEY (`lecture_id`, `lecturer_lecturer_id`),
  INDEX `fk_lecture_lecturer1_idx` (`lecturer_lecturer_id` ASC),
  CONSTRAINT `fk_lecture_lecturer1`
    FOREIGN KEY (`lecturer_lecturer_id`)
    REFERENCES `student_authorization`.`lecturer` (`lecturer_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `student_authorization`.`subject`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `student_authorization`.`subject` (
  `subject_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `lecturer_lecturer_id` INT NOT NULL,
  `lecture_lecture_id` INT NOT NULL,
  `lecture_lecturer_lecturer_id` INT NOT NULL,
  PRIMARY KEY (`subject_id`, `lecturer_lecturer_id`, `lecture_lecture_id`, `lecture_lecturer_lecturer_id`),
  INDEX `fk_subject_lecturer_idx` (`lecturer_lecturer_id` ASC),
  INDEX `fk_subject_lecture1_idx` (`lecture_lecture_id` ASC, `lecture_lecturer_lecturer_id` ASC),
  CONSTRAINT `fk_subject_lecturer`
    FOREIGN KEY (`lecturer_lecturer_id`)
    REFERENCES `student_authorization`.`lecturer` (`lecturer_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_subject_lecture1`
    FOREIGN KEY (`lecture_lecture_id` , `lecture_lecturer_lecturer_id`)
    REFERENCES `student_authorization`.`lecture` (`lecture_id` , `lecturer_lecturer_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `student_authorization`.`group`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `student_authorization`.`group` (
  `group_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `course` INT NULL,
  `lecturer_lecturer_id` INT NOT NULL,
  PRIMARY KEY (`group_id`, `lecturer_lecturer_id`),
  INDEX `fk_group_lecturer1_idx` (`lecturer_lecturer_id` ASC),
  CONSTRAINT `fk_group_lecturer1`
    FOREIGN KEY (`lecturer_lecturer_id`)
    REFERENCES `student_authorization`.`lecturer` (`lecturer_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `student_authorization`.`student`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `student_authorization`.`student` (
  `student_id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `group_group_id` INT NOT NULL,
  PRIMARY KEY (`student_id`, `group_group_id`),
  INDEX `fk_student_group1_idx` (`group_group_id` ASC),
  CONSTRAINT `fk_student_group1`
    FOREIGN KEY (`group_group_id`)
    REFERENCES `student_authorization`.`group` (`group_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `student_authorization`.`subject_has_student`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `student_authorization`.`subject_has_student` (
  `subject_subject_id` INT NOT NULL,
  `student_student_id` INT NOT NULL,
  PRIMARY KEY (`subject_subject_id`, `student_student_id`),
  INDEX `fk_subject_has_student_student1_idx` (`student_student_id` ASC),
  INDEX `fk_subject_has_student_subject1_idx` (`subject_subject_id` ASC),
  CONSTRAINT `fk_subject_has_student_subject1`
    FOREIGN KEY (`subject_subject_id`)
    REFERENCES `student_authorization`.`subject` (`subject_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_subject_has_student_student1`
    FOREIGN KEY (`student_student_id`)
    REFERENCES `student_authorization`.`student` (`student_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `student_authorization`.`authorities`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `student_authorization`.`authorities` (
  `username` VARCHAR(255) NOT NULL,
  `authority` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`username`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `student_authorization`.`authorities_has_user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `student_authorization`.`authorities_has_user` (
  `authorities_username` VARCHAR(255) NOT NULL,
  `user_username` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`authorities_username`, `user_username`),
  INDEX `fk_authorities_has_user_user1_idx` (`user_username` ASC),
  INDEX `fk_authorities_has_user_authorities1_idx` (`authorities_username` ASC),
  CONSTRAINT `fk_authorities_has_user_authorities1`
    FOREIGN KEY (`authorities_username`)
    REFERENCES `student_authorization`.`authorities` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_authorities_has_user_user1`
    FOREIGN KEY (`user_username`)
    REFERENCES `student_authorization`.`user` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
