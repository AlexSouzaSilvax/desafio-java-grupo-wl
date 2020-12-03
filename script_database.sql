CREATE DATABASE estudo;
USE estudo;

CREATE TABLE `estudo`.`usuario` (
  `id` INT NOT NULL auto_increment,
  `nome` VARCHAR(100) NOT NULL,
  `documento` VARCHAR(100) NOT NULL,
  `data_nascimento` DATE NOT NULL,
  `senha` VARCHAR(1000) NOT NULL,
  PRIMARY KEY (`id`));

INSERT usuario (nome,documento,data_nascimento,senha) VALUES ('Alex', '15325071785', STR_TO_DATE("03/03/2001", "%m/%d/%Y"),'123');
SELECT * FROM usuario;

DELIMITER $$
CREATE PROCEDURE `pc_insert_usuario` (
	IN IN_nome VARCHAR(100),
    IN IN_documento VARCHAR(100),
	IN IN_data_nascimento DATE,
    IN IN_senha VARCHAR(1000)
 )
BEGIN
	INSERT INTO usuario
    (nome, documento, data_nascimento, senha)
	VALUES 
    (IN_nome, IN_documento, IN_data_nascimento, IN_senha);
END $$

-- Teste
call estudo.pc_insert_usuario('Alex', '15325071785', "2001-03-03",'5480');
-- OK
SELECT * FROM usuario;


CREATE PROCEDURE `pc_update_usuario`
(
IN IN_id INT(11), 
INOUT INOUT_nome VARCHAR(100),
INOUT INOUT_documento VARCHAR(100),
INOUT INOUT_data_nascimento DATE,
INOUT INOUT_senha VARCHAR(1000)          
)
BEGIN
	UPDATE usuario
    SET
		nome  =  INOUT_nome,
        documento  =  INOUT_documento,
        data_nascimento = INOUT_data_nascimento,
        senha = INOUT_senha
	WHERE 
		id    =  IN_id;
END $$

CALL estudo.pc_update_usuario(1,'Alex é foda', '2222222222', "20111005",'100');

SELECT * FROM usuario;

DELIMITER $$
CREATE PROCEDURE `pc_delete_usuario`
(
IN IN_id INT(11)                                    
)
BEGIN
	DELETE FROM usuario
    WHERE
		id = IN_ipc_delete_usuariod;
END $$ 

CALL estudo.pc_delete_usuario(7);

SELECT * FROM usuario;
