CREATE DATABASE Certificaciones;

USE Certificaciones;

create table Usuario (
  id varchar(10) unique not null,
  clave varchar(100) not null,
  rol varchar(10) not null,
  Primary key(id)
);

create table Tipo (
  id varchar(10) unique not null,
  nombre varchar(30) not null,
  Primary key(id)
);

create table Documento (
  id varchar(10) unique not null,
  descripcion varchar(50) not null,
  monto float,
  timbres float,
  tipo varchar(10),
  Primary key(id)
);

create table Linea (
  id integer auto_increment not null,
  usuario varchar(10) not null,
  documento varchar(10) not null,
  cantidad integer,
  Primary key(id)
);
alter table Documento add foreign key (tipo) references Tipo(id);
alter table Linea add foreign key (usuario) references Usuario(id);
alter table Linea add foreign key (documento) references Documento(id);

insert into Usuario (id,clave,rol)
 values ('JPerez','$2a$12$UafqQkd9TVJxw9W4HoQ1eePssHEW6IPlX9VeKHWvMfiEeHGgsdOGO','CLI'); /* clave 111 */
 
 insert into Usuario (id,clave,rol)
 values ('MMata','$2a$12$z0dbkRqX1JSXOjAN9YX8tOWp.Es70TJl08ebtGgrwdOvgKe9dC67e','CLI'); /* clave 222 */
 
insert into Tipo (id,nombre) values ('001', 'Personas Juridicas');
insert into Tipo (id,nombre) values ('002', 'Bienes Inmuebles');
insert into Tipo (id,nombre) values ('003', 'Bienes Muebles');
insert into Tipo (id,nombre) values ('004', 'Catastro');
insert into Tipo (id,nombre) values ('005', 'Placas');
insert into Tipo (id,nombre) values ('006', 'Propiedad Intelectual');
insert into Tipo (id,nombre) values ('007', 'Alerta Registral');

insert into Documento (id,descripcion,monto,timbres,tipo)
 values ('001', 'Afectacion',2785,315,'001');
insert into Documento (id,descripcion,monto,timbres,tipo)
 values ('002', 'Cedula Juridica', 2785, 315,'001');
insert into Documento (id,descripcion,monto,timbres,tipo)
 values ('003', 'Historica de Movimientos', 2785, 315,'001');
insert into Documento (id,descripcion,monto,timbres,tipo)
 values ('004', 'Historica de Propiedades',2515,115,'002'); 
insert into Documento (id,descripcion,monto,timbres,tipo)
 values ('005', 'Literal de Inmuebles',2785,315,'002'); 
insert into Documento (id,descripcion,monto,timbres,tipo)
 values ('006', 'Indice de Personas',2515,115,'002');
insert into Documento (id,descripcion,monto,timbres,tipo)
 values ('007', 'Solicitud de Placas de Motos y remolques',10900,0,'005'); 
insert into Documento (id,descripcion,monto,timbres,tipo)
 values ('008', 'Solicitud de Placas de Vehículo',17600,0,'005'); 