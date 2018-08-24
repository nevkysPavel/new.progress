CREATE TABLE Clients (
  client_id         INTEGER AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(50)  NOT NULL,
  last_name  VARCHAR(100) NOT NULL,
  sex        VARCHAR(6)   NOT NULL,
  years      INTEGER      NOT NULL,
  height     INTEGER      NOT NULL,
  weight     INTEGER      NOT NULL
);

CREATE TABLE food_and_activity (
  id           INTEGER AUTO_INCREMENT PRIMARY KEY,
  protein      INTEGER NOT NULL,
  carbohydrate INTEGER NOT NULL,
  fat          INTEGER NOT NULL,
  kindOfSport  VARCHAR(20),
  duration_of_training INTEGER,
  localDate DATE,
  client_id INTEGER,
  CONSTRAINT FK_PersonOrder foreign key (client_id) references clients (client_id)
);

create index FKg9ptyre1tyaxjey10ob1fj1gs
  on food_and_activity (client_id);





