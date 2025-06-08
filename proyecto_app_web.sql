CREATE DATABASE proyecto_app_web;

drop database proyecto_app_web;
use proyecto_app_web;

select * from persona;
select * from producto;
select * from pedido;
select * from usuario;


drop table user;

DELETE FROM `user` WHERE name = 'aldair';



insert into user(name, password,role) values
("aldair", "123", "ADMIN");

insert into producto (nombre, autor, precio_unitario, stock, fecha_creacion) values
("Travesuras de la niña mala", "Mario Vargas Llosa", 100, 20, CURRENT_DATE),
("El túnel", "Ernesto Sábato", 90, 15, CURRENT_DATE),
("La historia del loco", "John Katzenbach", 120, 25, CURRENT_DATE),
("Cien años de soledad", "Gabriel García Márquez", 150, 30, CURRENT_DATE),
("No se lo digas a nadie", "Jaime Bayly", 80, 18, CURRENT_DATE),
("El amor en los tiempos del cólera", "Gabriel García Márquez", 110, 22, CURRENT_DATE),
("1984", "George Orwell", 95, 20, CURRENT_DATE),
("Rebelión en la granja", "George Orwell", 85, 17, CURRENT_DATE),
("Rayuela", "Julio Cortázar", 100, 12, CURRENT_DATE),
("El principito", "Antoine de Saint-Exupéry", 70, 50, CURRENT_DATE),
("Los miserables", "Victor Hugo", 140, 10, CURRENT_DATE);
