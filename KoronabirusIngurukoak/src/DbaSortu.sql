/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Aitor
 * Created: 13 abr. 2020
 */

DROP DATABASE KoronabirusDBa;
CREATE DATABASE KoronabirusDBa;
USE KoronabirusDBa
CREATE TABLE Herrialdea(
	Herrialdea VARCHAR(50) PRIMARY KEY,
	Populazioa INT,
	Azalera Double);

CREATE TABLE Estatistikak(
        Eguna DATE,
        Kutsatuak INT,
        Hildakoak INT,
        Herrialdea VARCHAR(50),
        PRIMARY KEY (eguna,herrialdea),
        CONSTRAINT herrialdea FOREIGN KEY (herrialdea) REFERENCES herrialdea(herrialdea)
        );

CREATE USER ikaslea IDENTIFIED BY 'ikaslea';
GRANT SELECT,INSERT,UPDATE,DELETE ON KoronabirusDba.* TO ikaslea;

