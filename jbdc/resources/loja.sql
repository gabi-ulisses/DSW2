CREATE DATABASE loja;
USE loja;

DROP TABLE IF EXISTS Produto;
CREATE TABLE Produto(
	id INT,
    nome VARCHAR(100)
);

INSERT INTO Produto(id, nome) 
VALUES	(1, 'Teclado USB'),
		(2, 'Mouse'),
		(3, 'Headphone'),
		(4, 'Monitor');

SELECT * FROM Produto;
