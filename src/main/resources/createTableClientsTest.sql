CREATE TABLE Clients (
  id         INTEGER AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(50)  NOT NULL,
  last_name  VARCHAR(100) NOT NULL,
  sex        VARCHAR(6)   NOT NULL,
  years       INTEGER      NOT NULL,
  height     INTEGER      NOT NULL,
  weight     INTEGER      NOT NULL
);

