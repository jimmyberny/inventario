create database inventario;
use inventario;

create table area (
	id varchar(40) not null,
	nombre varchar(40) not null,
	constraint pk_area primary key(id)
) engine = InnoDB;

create table empleado(
	id varchar(40) not null,
	nombre varchar(40) not null,
	paterno varchar(40) not null,
	materno varchar(40) null,
	clave int not null,
	id_area varchar(40) not null,
	constraint pk_empleado primary key(id),
	constraint fk_e_a_area foreign key(id_area) references area(id)
	-- id_usuario varchar(40) null,
	-- contraint fk_e_u_usuario foreign key(id_usuario) references usuario(id)
) engine = InnoDB;


create table usuario(
	id_empleado varchar(40) not null,
	usuario varchar(40) not null,
	contrasena varchar(40) not null,
	constraint pk_usuario primary key(id_empleado),
	constraint fk_u_e_empleado foreign key(id_empleado) references empleado(id)
) engine = InnoDB;

create table tipo_equipo(
	id varchar(40) not null,
	nombre varchar(40) not null,
	constraint pk_tipo_equipo primary key(id)
) engine = InnoDB;

create table equipo_computo(
	id varchar(40) not null,
	serie varchar(20) not null,
	activo_fijo varchar(10) not null,
	marca varchar(30) not null,
	modelo varchar(20) not null,
	descripcion varchar(255) not null,
	caracteristicas blob not null,
	id_tipo varchar(40) not null,
	id_empleado varchar(40) null,
	estado varchar(20) not null,
	constraint pk_equipo_computo primary key(id),
	constraint fk_ec_te_tipo foreign key(id_tipo) references tipo_equipo(id),
	constraint fk_ec_e_empleado foreign key(id_empleado) references empleado(id)
) engine = InnoDB;

create table programa (
	id varchar(40) not null,
	nombre varchar(120) not null,
	version varchar(120) not null,
	constraint pk_programa primary key(id)
) engine = InnoDB;

create table equipo_programa (
    id varchar(40) not null,
	id_equipo varchar(40) not null,
	id_programa varchar(40) not null,
	vigencia datetime null,
	constraint pk_equipo_programa primary key(id),
	constraint fk_ep_e_equipo foreign key(id_equipo) references equipo_computo(id),
	constraint fk_ep_p_programa foreign key(id_programa) references programa(id)
) engine = InnoDB;

create table evento (
	id varchar(40) not null,
	id_equipo varchar(40) not null,
	tipo varchar(40) not null, -- mantenimiento, instalacion, limpieza
	nombre varchar(40) not null,
	instruccion varchar(255) not null,
	observaciones varchar(255) not null,
	fecha_programada datetime not null,
	fecha_realizado datetime null, -- Si es null, significa que esta pendiente
	constraint pk_evento primary key(id),
	constraint fk_e_ec_equipo foreign key(id_equipo) references equipo_computo(id)
) engine = InnoDB;




