CREATE DATABASE cine;
--USE cine;

CREATE TABLE categoria(
  id int AUTO_INCREMENT NOT NULL,
  nombre varchar(100) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE pelicula(
     id bigint AUTO_INCREMENT NOT NULL,
     titulo varchar(200) NOT NULL,
     descripcion text,
     sinopsis text,
     rating int NOT NULL DEFAULT 0,
     registered date,
     updated date,
     estado int NOT NULL DEFAULT 1,
     categoria int NOT NULL REFERENCES categoria(id),
     PRIMARY KEY (id)
);