CREATE TABLE Clients (
  id         INTEGER AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(50)  NOT NULL,
  last_name  VARCHAR(100) NOT NULL,
  sex        VARCHAR(6)   NOT NULL,
  year       INTEGER      NOT NULL,
  height     INTEGER      NOT NULL,
  weight     INTEGER      NOT NULL
);

INSERT INTO Clients (first_name, last_name, sex, year, height, weight)
VALUES ('Garvrilov', 'Roma', 'MAN', '1991', '180', '90');

INSERT INTO Clients (first_name, last_name, sex, year, height, weight)
VALUES ('Nevkys', 'Pavel', 'MAN', '1990', '175', '85');

INSERT INTO Clients (first_name, last_name, sex, year, height, weight)
VALUES ('Kylga', 'Olesa', 'WOMAN', '1993', '165', '55');

INSERT INTO Clients (first_name, last_name, sex, year, height, weight)
VALUES ('Borov', 'Katya', 'WOMAN', '1992', '175', 56);