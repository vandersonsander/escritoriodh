CREATE DATABASE IF NOT EXISTS `escritorio_dh_vanderson`;

USE `escritorio_dh_vanderson`;

DROP TABLE IF EXISTS `folha_de_ponto`;
DROP TABLE IF EXISTS `prod_pedido`;
DROP TABLE IF EXISTS `pedido`;
DROP TABLE IF EXISTS `gerente`;
DROP TABLE IF EXISTS `produto`;
DROP TABLE IF EXISTS `telefone`;
DROP TABLE IF EXISTS `endereco`;
DROP TABLE IF EXISTS `cliente`;
DROP TABLE IF EXISTS `funcionario`;
DROP TABLE IF EXISTS `usuario`;
DROP TABLE IF EXISTS `autorizacao`;

CREATE TABLE `cliente` (
	`id_cliente` INT AUTO_INCREMENT,
    `nome` VARCHAR(150) NOT NULL,
    `cpf` VARCHAR(20) NOT NULL,
    `email` VARCHAR(150) NOT NULL,
    PRIMARY KEY (`id_cliente`)
) ENGINE=InnoDB;

CREATE TABLE `funcionario` (
	`id_funcionario` INT AUTO_INCREMENT,
	`ctps` VARCHAR(50) NOT NULL,
    `data_admissao` DATE NOT NULL,
    `data_demissao` DATE NULL,
    `salario` DECIMAL(10,2),
    `nome` VARCHAR(150) NOT NULL,
    `cpf` VARCHAR(20) NOT NULL,
    `rg` VARCHAR(20) NOT NULL,
    `email` VARCHAR(150) NOT NULL,
    `senha` VARCHAR(50) NOT NULL,
    `ativo` TINYINT(1) NOT NULL DEFAULT 1,
    PRIMARY KEY (`id_funcionario`)
) ENGINE=InnoDB;

CREATE TABLE `gerente` (
	`id_gerente` INT,
    PRIMARY KEY (`id_gerente`),
    UNIQUE INDEX `id_gerente_UNIQUE` (`id_gerente` ASC),
    CONSTRAINT `fk_gerente_funcionario`
		FOREIGN KEY (`id_gerente`)
		REFERENCES `funcionario`(`id_funcionario`)
        ON DELETE CASCADE
        ON UPDATE CASCADE
) ENGINE=InnoDB;

CREATE TABLE `telefone` (
	`id_telefone` INT AUTO_INCREMENT,
	`ddd` VARCHAR(5) NOT NULL,
    `numero` VARCHAR(13) NOT NULL,
    `id_funcionario` INT NULL,
    `id_cliente` INT NULL,
    PRIMARY KEY (`id_telefone`),
    CONSTRAINT `fk_telefone_funcionario`
		FOREIGN KEY (`id_funcionario`)
		REFERENCES `funcionario`(`id_funcionario`)
        ON DELETE SET NULL
        ON UPDATE CASCADE,
	CONSTRAINT `fk_telefone_cliente`
		FOREIGN KEY (`id_cliente`)
		REFERENCES `cliente`(`id_cliente`)
        ON DELETE SET NULL
        ON UPDATE CASCADE
) ENGINE=InnoDB;

CREATE TABLE `endereco` (
    `id_endereco` INT AUTO_INCREMENT,
    `logradouro` VARCHAR(150),
    `numero` VARCHAR(10),
    `complemento` VARCHAR(150),
    `cep` VARCHAR(20),
    `cidade` VARCHAR(100),
    `estado` VARCHAR(2),
    `id_funcionario` INT NULL,
    `id_cliente` INT NULL,
    PRIMARY KEY (`id_endereco`),
    CONSTRAINT `fk_endereco_funcionario`
		FOREIGN KEY (`id_funcionario`)
		REFERENCES `funcionario`(`id_funcionario`)
        ON DELETE SET NULL
        ON UPDATE CASCADE,
	CONSTRAINT `fk_endereco_cliente`
		FOREIGN KEY (`id_cliente`)
		REFERENCES `cliente`(`id_cliente`)
        ON DELETE SET NULL
        ON UPDATE CASCADE
)  ENGINE=InnoDB;

CREATE TABLE `produto`(
	`id_produto` INT AUTO_INCREMENT,
    `nome` VARCHAR(100) NOT NULL,
    `descricao` VARCHAR(255),
    `estoque` INT,
    `preco` DECIMAL(10,2),
    PRIMARY KEY (`id_produto`)
) ENGINE=InnoDB;

CREATE TABLE `pedido`(
	`nfe` VARCHAR(50),
    `data_emissao` DATE,
    `valor_total` DECIMAL(10,2),
    `status_pedido` VARCHAR(20),
    `id_cliente` INT,
    `descricao` VARCHAR(255),
    PRIMARY KEY (`nfe`),
    CONSTRAINT `fk_pedido_cliente`
		FOREIGN KEY (`id_cliente`)
		REFERENCES `cliente`(`id_cliente`)
        ON DELETE CASCADE
        ON UPDATE CASCADE
) ENGINE=InnoDB;

CREATE TABLE `prod_pedido`(
	`id_produto` INT NOT NULL,
    `nfe` VARCHAR(50) NOT NULL,
    `quantidade` INT,
    PRIMARY KEY (`id_produto`, `nfe`),
    CONSTRAINT `fk_prod_pedido_produto`
		FOREIGN KEY (`id_produto`)
		REFERENCES `produto`(`id_produto`)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
	CONSTRAINT `fk_prod_pedido_pedido`
		FOREIGN KEY (`nfe`)
		REFERENCES `pedido`(`nfe`)
        ON DELETE CASCADE
        ON UPDATE CASCADE
) ENGINE=InnoDB;

CREATE TABLE `folha_de_ponto` (
	`id_folha_de_ponto` INT AUTO_INCREMENT,
    `data_registro` DATE DEFAULT (CURRENT_DATE),
	`hora_entrada` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `hora_saida` DATETIME,
    `id_funcionario` INT,
    PRIMARY KEY (`id_folha_de_ponto`),
    UNIQUE INDEX `data_registro_id_funcionario_UNIQUE` (`data_registro` DESC, `id_funcionario`),
    CONSTRAINT `fk_folha_de_ponto_funcionario`
		FOREIGN KEY (`id_funcionario`)
		REFERENCES `funcionario`(`id_funcionario`)
        ON DELETE CASCADE
        ON UPDATE CASCADE
) ENGINE=InnoDB;

CREATE TABLE `autorizacao` (
	`email` VARCHAR(150) NOT NULL,
    `autoridade` VARCHAR(20) NOT NULL,
    PRIMARY KEY (`email`)
) ENGINE=InnoDB;

INSERT INTO `funcionario` (`ctps`, `id_funcionario`, `data_admissao`, `salario`,
	`nome`, `cpf`, `rg`, `email`, `senha`)
VALUES ("62752160384", 1, "2015-05-02", 9480.50, 
	"Vanderson Sander", "227.559.150-80", "37.566.320-4", "vanderson.sander@escritoriodh.com", "123456");
INSERT INTO `funcionario` (`ctps`, `id_funcionario`, `data_admissao`, `salario`,
	`nome`, `cpf`, `rg`, `email`, `senha`)
VALUES ("95009065849", 2, "2016-02-28", 5601.30,
	"Caroline Sander", "950.159.480-77", "27.189.274-2", "caroline.sander@escritoriodh.com", "123456");
INSERT INTO `funcionario` (`ctps`, `id_funcionario`, `data_admissao`, `salario`,
	`nome`, `cpf`, `rg`, `email`, `senha`)
VALUES ("47385199261", 3, "2018-03-21", 2506.10,
	"Eduardo Martins", "7439.349.320-61", "39.861.263-8", "eduardo.martins@escritoriodh.com", "123456");
INSERT INTO `funcionario` (`ctps`, `id_funcionario`, `data_admissao`, `salario`,
	`nome`, `cpf`, `rg`, `email`, `senha`)
VALUES ("6085555830-2", 4, "2020-01-01", 1004.30,
	"Sabrina Silva", "498.873.170-71", "27.639.291-7", "sabrina.silva@escritoriodh.com", "123456");
INSERT INTO `funcionario` (`ctps`, `id_funcionario`, `data_admissao`, `salario`,
	`nome`, `cpf`, `rg`, `email`, `senha`)
VALUES ("53721043107", 5, "2017-05-31", 5601.30,
	"Joao Antonio", "166.297.860-09", "31.564.692-5", "joao.antonio@escritoriodh.com", "123456");

INSERT INTO `gerente` (`id_gerente`)
VALUES (1);
INSERT INTO `gerente` (`id_gerente`)
VALUES (2);

INSERT INTO `cliente` (`nome`, `cpf`, `email`)
VALUES ("Theofilo Teodoro", "654.723.120-86", "theofilo.teodoro@hotmail.com");
INSERT INTO `cliente` (`nome`, `cpf`, `email`)
VALUES ("Alberto Santos", "168.013.240-71", "alberto.santos@hotmail.com");
INSERT INTO `cliente` (`nome`, `cpf`, `email`)
VALUES ("Antonio Gomes", "028.932.150-68", "antonio.gomes@hotmail.com");
INSERT INTO `cliente` (`nome`, `cpf`, `email`)
VALUES ("Diogenes Schwarzer", "445.288.890-99", "diogenes.schwarzer@hotmail.com");
INSERT INTO `cliente` (`nome`, `cpf`, `email`)
VALUES ("Maicon Pereira", "169.429.470-67", "maicon.pereira@hotmail.com");

INSERT INTO `endereco` (`cep`, `logradouro`, `numero`, `complemento`, `cidade`, `estado`, `id_funcionario`)
VALUES ("02124-056", "Rua Taka", "1205", "Casa 2", "São Paulo", "SP", 1);
INSERT INTO `endereco` (`cep`, `logradouro`, `numero`, `complemento`, `cidade`, `estado`, `id_funcionario`)
VALUES ("03567-016", "Rua Nigata", "45", "", "São Paulo", "SP", 2);
INSERT INTO `endereco` (`cep`, `logradouro`, `numero`, `complemento`, `cidade`, `estado`, `id_funcionario`)
VALUES ("05698-001", "Av. das Cerejeiras", "705", "", "São Paulo", "SP", 3);
INSERT INTO `endereco` (`cep`, `logradouro`, `numero`, `complemento`, `cidade`, `estado`, `id_funcionario`)
VALUES ("12347-040", "Rua Takamoto", "30", "", "São Paulo", "SP", 4);
INSERT INTO `endereco` (`cep`, `logradouro`, `numero`, `complemento`, `cidade`, `estado`, `id_funcionario`)
VALUES ("65490-030", "Rua Barão de Itapetininga", "503", "Apto 25", "São Paulo", "SP", 5);
INSERT INTO `endereco` (`cep`, `logradouro`, `numero`, `complemento`, `cidade`, `estado`, `id_cliente`)
VALUES ("03059-100", "Rua Direita", "650", "Apto 60", "São Paulo", "SP", 1);
INSERT INTO `endereco` (`cep`, `logradouro`, `numero`, `complemento`, `cidade`, `estado`, `id_cliente`)
VALUES ("05687-101", "Rua 25 de Março", "5000", "Apto 310", "São Paulo", "SP", 2);
INSERT INTO `endereco` (`cep`, `logradouro`, `numero`, `complemento`, `cidade`, `estado`, `id_cliente`)
VALUES ("08960-035", "Av. Consolação", "3500", "Apto 530 Bloco 2", "São Paulo", "SP", 3);
INSERT INTO `endereco` (`cep`, `logradouro`, `numero`, `complemento`, `cidade`, `estado`, `id_cliente`)
VALUES ("13105-305", "Av. Paulista", "8000", "Apto 80 BLoco 3", "São Paulo", "SP", 4);
INSERT INTO `endereco` (`cep`, `logradouro`, `numero`, `complemento`, `cidade`, `estado`, `id_cliente`)
VALUES ("90556-102", "Av. Guilherme Cotching", "4651", "Apto 51", "São Paulo", "SP", 5);

INSERT INTO `telefone` (`ddd`, `numero`, `id_funcionario`)
VALUES ("11", "99434-1594", 1);
INSERT INTO `telefone` (`ddd`, `numero`, `id_funcionario`)
VALUES ("11", "95388-1424", 2);
INSERT INTO `telefone` (`ddd`, `numero`, `id_funcionario`)
VALUES ("11", "91676-0644", 3);
INSERT INTO `telefone` (`ddd`, `numero`, `id_funcionario`)
VALUES ("11", "99580-8913", 4);
INSERT INTO `telefone` (`ddd`, `numero`, `id_funcionario`)
VALUES ("11", "91562-2414", 5);
INSERT INTO `telefone` (`ddd`, `numero`, `id_cliente`)
VALUES ("11", "93805-6227", 1);
INSERT INTO `telefone` (`ddd`, `numero`, `id_cliente`)
VALUES ("11", "92978-0770", 2);
INSERT INTO `telefone` (`ddd`, `numero`, `id_cliente`)
VALUES ("11", "94561-5015", 3);
INSERT INTO `telefone` (`ddd`, `numero`, `id_cliente`)
VALUES ("11", "91880-8240", 4);
INSERT INTO `telefone` (`ddd`, `numero`, `id_cliente`)
VALUES ("11", "90156-5301", 5);
INSERT INTO `telefone` (`ddd`, `numero`, `id_funcionario`)
VALUES ("11", "94932-8071", 1);
INSERT INTO `telefone` (`ddd`, `numero`, `id_funcionario`)
VALUES ("11", "97074-5836", 2);
INSERT INTO `telefone` (`ddd`, `numero`, `id_funcionario`)
VALUES ("11", "99479-3284", 3);
INSERT INTO `telefone` (`ddd`, `numero`, `id_cliente`)
VALUES ("11", "94886-9313", 4);
INSERT INTO `telefone` (`ddd`, `numero`, `id_cliente`)
VALUES ("11", "98150-5361", 5);

INSERT INTO `produto` (`nome`, `estoque`, `preco`)
VALUES ("Monitor 22p Widescreen", 20, 1200.50);
INSERT INTO `produto` (`nome`, `estoque`, `preco`)
VALUES ("Mouse Gamer", 30, 320.00);
INSERT INTO `produto` (`nome`, `estoque`, `preco`)
VALUES ("Webcam FullHD 1080p", 15, 420.50);
INSERT INTO `produto` (`nome`, `estoque`, `preco`)
VALUES ("Teclado Mecânico", 100, 620.20);
INSERT INTO `produto` (`nome`, `estoque`, `preco`)
VALUES ("Placa de Video 2080 TI", 10, 3250.40);
INSERT INTO `produto` (`nome`, `estoque`, `preco`)
VALUES ("Memória RAM 16GB DDR 4", 100, 210.50);
INSERT INTO `produto` (`nome`, `estoque`, `preco`)
VALUES ("Gabinete Personalizado Tampa Acrilico", 80, 300.30);
INSERT INTO `produto` (`nome`, `estoque`, `preco`)
VALUES ("WaterCooler Corsair", 200, 450.10);
INSERT INTO `produto` (`nome`, `estoque`, `preco`)
VALUES ("Fonte Corsair 650W 80 Plus", 45, 810.10);

INSERT INTO `pedido` (`nfe`, `data_emissao`, `valor_total`, `status_pedido`, `id_cliente`)
VALUES ("20200000001", "2020-05-15", 1200.50, "concluído", 1);
INSERT INTO `pedido` (`nfe`, `data_emissao`, `valor_total`, `status_pedido`, `id_cliente`)
VALUES ("20200000002", "2020-05-15", 320.00, "concluído", 1);
INSERT INTO `pedido` (`nfe`, `data_emissao`, `valor_total`, `status_pedido`, `id_cliente`)
VALUES ("20200000003", "2020-07-10", 420.50, "pendente", 2);
INSERT INTO `pedido` (`nfe`, `data_emissao`, `valor_total`, `status_pedido`, `id_cliente`)
VALUES ("20200000004", "2020-06-12", 620.20, "concluído", 3);
INSERT INTO `pedido` (`nfe`, `data_emissao`, `valor_total`, `status_pedido`, `id_cliente`)
VALUES ("20200000005", "2020-08-20", 3250.40, "cancelado", 4);
INSERT INTO `pedido` (`nfe`, `data_emissao`, `valor_total`, `status_pedido`, `id_cliente`)
VALUES ("20200000006", "2020-01-03", 510.80, "pendente", 5);
INSERT INTO `pedido` (`nfe`, `data_emissao`, `valor_total`, `status_pedido`, `id_cliente`)
VALUES ("20200000007", "2020-05-15", 1260.20, "cancelado", 5);
INSERT INTO `pedido` (`nfe`, `data_emissao`, `valor_total`, `status_pedido`, `id_cliente`)
VALUES ("20200000008", "2020-05-15", 2520.40, "pendente", 5);

INSERT INTO `prod_pedido` (`id_produto`, `nfe`, `quantidade`)
VALUES (1, "20200000001", 1);
INSERT INTO `prod_pedido` (`id_produto`, `nfe`, `quantidade`)
VALUES (2, "20200000002", 1);
INSERT INTO `prod_pedido` (`id_produto`, `nfe`, `quantidade`)
VALUES (3, "20200000003", 1);
INSERT INTO `prod_pedido` (`id_produto`, `nfe`, `quantidade`)
VALUES (4, "20200000004", 1);
INSERT INTO `prod_pedido` (`id_produto`, `nfe`, `quantidade`)
VALUES (5, "20200000005", 1);
INSERT INTO `prod_pedido` (`id_produto`, `nfe`, `quantidade`)
VALUES (6, "20200000006", 1);
INSERT INTO `prod_pedido` (`id_produto`, `nfe`, `quantidade`)
VALUES (7, "20200000006", 1);
INSERT INTO `prod_pedido` (`id_produto`, `nfe`, `quantidade`)
VALUES (8, "20200000007", 1);
INSERT INTO `prod_pedido` (`id_produto`, `nfe`, `quantidade`)
VALUES (8, "20200000008", 2);

INSERT INTO `autorizacao` (`email`, `autoridade`)
VALUES ("vanderson.sander@escritoriodh.com", "ROLE_GERENTE");
INSERT INTO `autorizacao` (`email`, `autoridade`)
VALUES ("caroline.sander@escritoriodh.com", "ROLE_GERENTE");
INSERT INTO `autorizacao` (`email`, `autoridade`)
VALUES ("eduardo.martin@escritoriodh.com", "ROLE_FUNC");
INSERT INTO `autorizacao` (`email`, `autoridade`)
VALUES ("sabrina.silva@escsritoriodh.com", "ROLE_FUNC");
INSERT INTO `autorizacao` (`email`, `autoridade`)
VALUES ("joao.antonio@escritoriodh.com", "ROLE_FUNC");
