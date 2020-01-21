--CREATE DATABASE INVENTARIO;
drop table if exists detallenota;
drop table if exists notacredito;

drop table if exists detallefactura;
drop table if exists factura;
drop table if exists tipofactura;

drop table if exists detallemovimiento;
drop table if exists movimientos ;
drop table if exists tipomovimiento;

drop table if exists usuarios;
drop table if exists perfiles;

drop table if exists costos;
drop table if exists articulos CASCADE;
drop table if exists subcategorias;
drop table if exists categorias;
drop table if exists examenVisual;

drop table if exists citas;

drop table if exists responsables;
drop table if exists tiporesponsable;
drop table if exists ciudades;
drop table if exists provincia;
drop table if exists ingresoAlSistema;
drop table if exists empresa;
drop table if exists estados;
drop table if exists periodo;










------------------------------------------------------------------------------------------------------------------------------------------------------



create table estados(
codestado char not null primary key,
descripcion character varying(30)
);



insert into estados values ('A','ACTIVO');
insert into estados values ('I','INACTIVO');
insert into estados values ('E','ERROR');
insert into estados values ('C','CORRECTO');

 create table perfiles(
  codperfil integer not null,
  descripcion character varying(30) not null,
  constraint pkey_codperfil primary key(codperfil)
);
insert into perfiles values(1,'ADMINISTRADOR'); 
insert into perfiles values(2,'SECRETARIA');
insert into perfiles values(3,'ROOT');
insert into perfiles values(4,'MÉDICO');

----------------------------------------------------------------------------------

create table Empresa(
  identificacion character varying (13) not null,
  razonsocial character varying (80) not null,
  codciudad character varying (6) not null,
  direccion character varying (100) not null,
  telefono character varying (10) null,
  iva float not null
);

----------------------------------------------------------------------------------
INSERT INTO empresa(identificacion, razonsocial, codciudad, direccion, telefono, iva)
    VALUES ('0999991327413', 'Optica', '1201', 'BABAHOYO', '0999999999', 12);

----------------------------------------------------------------------------------

create table usuarios
( 
  codperfil integer not null,
  codusuario character varying(10) not null,
  nombre  character varying(40) not null,
  clave character varying(10) not null,
  codestado char not null default 'A',
  constraint pkey_codusurio primary key(codusuario),
  constraint fkey_codperfil foreign key(codperfil) references perfiles(codperfil) on update cascade on delete restrict,
  constraint fkey_codestadou foreign key(codestado) references estados(codestado) on update cascade on delete restrict
);
insert into usuarios values(1,'ADMIN','ADMINISTRADOR','ADMIN','A');


drop sequence if exists sig_ingreso;
create sequence sig_ingreso;

create table ingresoAlSistema(
 codiniciosis int default nextval('sig_ingreso') not null,
 codusuario character varying(10) not null,
 codestado char not null REFERENCES estados on delete restrict on update cascade,
 fecha timestamp default current_timestamp not null,
 isusuario boolean not null
); 



drop type if exists getUser cascade;
create type getUser as (codperfil integer, codusua character varying, nombre character varying, cleve character varying, codestado char);

create or replace function obtenerUsuario(text,text) returns setof getUser as $$
declare
tabla getUser%rowtype;
begin 
  if not exists (select codusuario from usuarios where codusuario =$1 and clave=$2)then 
	insert into ingresoAlSistema values(default, $1,'E',default,isUser($1));
  end if;
  for tabla in select * from usuarios where codusuario=$1 and clave=$2 loop
	insert into ingresoAlSistema values(default, $1,'C',default,isUser($1));
  return next tabla;
  end loop;
end
$$language plpgsql;

 

create or replace function isUser(text) returns boolean as
$$
begin
 if exists (select codusuario from usuarios where codusuario =$1)then
 return true;
else
return false;
 end if;
end
$$language plpgsql;




 

drop sequence if exists  sig_cat;
create sequence sig_cat;

create table categorias
(
  codcategoria integer not null default nextval('sig_cat'),
  descripcion  character varying(40) not null UNIQUE,
  constraint pkey_codcategoria primary key(codcategoria)  
);

 
drop sequence if exists  sig_sub_cat;
create sequence sig_sub_cat;

create table subcategorias
(
  codcategoria integer not null ,
  codsubcategoria integer not null default nextval('sig_sub_cat'),
  descripcion  character varying(40) not null UNIQUE,
  constraint pkey_codsubcategoria primary key(codsubcategoria),
  constraint fkey_codcategoria foreign key(codcategoria) references categorias(codcategoria) on update cascade on delete restrict
);
------------------------------------------------------------------------------------
create or replace function getIdCategoria(text) returns int as
$$
 declare id int;
begin
 if exists (select codcategoria from categorias where descripcion = upper($1)) then
  select codcategoria into id from categorias where descripcion = upper($1);
 else
  id:=nextval('sig_cat');
  insert into categorias values(id,upper($1));
 end if;
 return id;
end
$$
language plpgsql;

---------------------------------------------------------------------
 
------------------------------------------------------------------------------------
create or replace function getIdSubCategoria(int,text) returns int as
$$
 declare id int;
begin
 if exists (select codsubcategoria from subcategorias where descripcion = upper($2) and codcategoria=$1) then
  select codsubcategoria into id from subcategorias where descripcion = upper($2) and codcategoria=$1;
 else
  id:=nextval('sig_sub_cat');
  insert into subcategorias values ($1,id,upper($2));
 end if;
 return id;
end
$$
language plpgsql;


---------------------------------------------------------------------

create table articulos
(
  codcategoria integer not null,
  codsubcategoria integer not null,
  codarticulo character varying(6) not null,
  descripcion  character varying(40) not null,
  grabaiva character default 'N',
  costo float default 0,
  pvp float default 0,
  stock float default 0,
  estado char default 'A',
  constraint pkey_codarticulo primary key(codarticulo),
  constraint fkey_codcategoria2 foreign key(codcategoria) references categorias(codcategoria) on update cascade on delete restrict,
  constraint fkey_codsubcategoria foreign key(codsubcategoria) references subcategorias(codsubcategoria) on update cascade on delete restrict 
);


  





create table tipomovimiento
(
  codtipomovimiento character varying (3) not null,
  descripcion character varying (50) not null,
  naturaleza character varying (2) not null,
  constraint pkey_codtipomovimiento primary key(codtipomovimiento)
);

insert into tipomovimiento values ('COM','COMPRAS','I');
insert into tipomovimiento values ('DPC','DEVOLUCIÓN DE COMPRAS','I');
insert into tipomovimiento values ('BAI','BALANCE INICIAL','I');
insert into tipomovimiento values ('EPV','VENTAS','E');
insert into tipomovimiento values ('DPV','DEVOLUCIÓN DE VENTAS','E');
insert into tipomovimiento values ('PER','PERDIDA O DETERIORO','E');

create table provincia(
 codprovincia character varying(2) not null primary key,
 descripcion character varying(30) not null
);

insert into provincia values('12','LOS RIOS');
insert into provincia values('01','AZUAY');

create table ciudades
(
  codciudad character varying (4) not null,
  codprovincia character varying(2) not null ,
  nombreciudad character varying (60) not null,
  constraint pkey_codciudad primary key(codciudad),
  constraint fk_cod_c_p foreign key (codprovincia) references provincia(codprovincia) on update cascade on delete restrict
);

insert into ciudades values('1201','12','BABAHOYO');
insert into ciudades values('0101','01','CUENCA');

create table tiporesponsable
(
  codtiporesponsable character not null,
  descripcion character varying (20)not null,
  constraint pkey_codtiporesponsable primary key(codtiporesponsable)
);
insert into tiporesponsable values ('P','PROVEEDORES');
insert into tiporesponsable values ('C','CLIENTES');
insert into tiporesponsable values ('E','EMPRESA');

create table responsables
(
  codtiporesponsable character not null,
  identificacion character varying (13) not null,
  razonsocial character varying (80) not null,
  codciudad character varying (6) not null,
  direccion character varying (100) not null,
  telefono character varying (10) null,
  contacto character varying (50) null,
  constraint pkey_identificacion primary key(codtiporesponsable, identificacion),
  constraint fkey_codtiporesponsable foreign key(codtiporesponsable) references tiporesponsable(codtiporesponsable) on update cascade on delete restrict,
  constraint fkey_codciudad foreign key(codciudad) references ciudades(codciudad) on update cascade on delete restrict
);

INSERT INTO responsables(
            codtiporesponsable, identificacion, razonsocial, codciudad, direccion, 
            telefono, contacto)
    VALUES ('P', '1207372002', 'JUAN MOSQUERA', '1201', 'BARREIRO','0998654321','0991327413');

INSERT INTO responsables(
            codtiporesponsable, identificacion, razonsocial, codciudad, direccion, 
            telefono, contacto)
    VALUES ('P', '0912345678', 'PEDRO MOSQUERA', '1201', 'BARREIRO','0998654321','0991327413');

INSERT INTO responsables(
            codtiporesponsable, identificacion, razonsocial, codciudad, direccion, 
            telefono, contacto)
    VALUES ('P', '0912345673', 'PEDRO CAMACHO', '0101', 'BARREIRO','0998654321','0991327413');

INSERT INTO responsables(
            codtiporesponsable, identificacion, razonsocial, codciudad, direccion, 
            telefono, contacto)
    VALUES ('C', '1207372002', 'JOSE MOSQUERA', '1201', 'BARREIRO','0991327413','0991327413');

INSERT INTO responsables(
            codtiporesponsable, identificacion, razonsocial, codciudad, direccion, 
            telefono, contacto)
    VALUES ('C', '0912345678', 'ALBERTO CASTRO', '1201', 'BARREIRO','0991327413','0991327413');

INSERT INTO responsables(codtiporesponsable,identificacion, razonsocial, codciudad, direccion, telefono, contacto)
    VALUES ('E','0999991327413', 'Optica ', '1201', 'MONTALVO', '0999999999', '0999999999');




create table examenVisual(
 idexamen serial not null primary key,
 codtiporesponsable character not null,
  identificacion character varying (13) not null,
 edad int not null,
 direccion character varying (100) not null,
 celular character varying (10) not null,
 tipoluna character varying (50) not null,
 odeje int not null,
 oieje int not null,
 odcyl int not null,
 oicyl int not null,
 adddp int not null,
 addalt int  not null 
);




create table movimientos(
codtipomovimiento character varying (3) not null,
naturaleza character not null,
codusuario character varying (10) not null,
codtiporesponsable character not null,
identificacion character varying (13) not null,
codmovimiento  bigint not null,
concepto character varying (200) not null,
fechamovimiento date not null,
totalmovimiento float default 0,
fechahoraingreso timestamp default now(),
fechahoraactualizacion timestamp null,
fechahoraanulacion timestamp null,
usuarioanulacion character varying (10) null,
usuarioaactualizacion character varying (10) null,
estado character default 'A',
idperiodo bigint default 0,
constraint pkey_codmovimiento primary key(codmovimiento),
constraint fkey_codtipomovimiento foreign key(codtipomovimiento) references tipomovimiento(codtipomovimiento) on update cascade on delete restrict,
constraint fkey_identificacion foreign key(codtiporesponsable, identificacion) references responsables(codtiporesponsable, identificacion) on update cascade on delete restrict 
);


create table citas(
 idcitas serial not null primary key,
 observacion character varying(100),
 codtiporesponsable character not null,
 identificacion character varying (13) not null,
);



create table detallemovimiento(
codmovimiento bigint not null,
codarticulo character varying(20) not null,
orden integer not null,
Cantidad float not null,
costo float not null, 
costotal float not null,
cantidadTotal float not null,
costounitariototal float not null,
constraint fkey_codarticulo_detallemovimiento foreign key(codarticulo)references articulos(codarticulo) on update cascade on delete restrict,
constraint fkey_codmovimiento_detallemovimiento foreign key(codmovimiento)references movimientos(codmovimiento) on update cascade on delete restrict
);


--------------------------------------------------------------------------------------------------------------------------------
 create or replace function agg_movimiento(text,char,text,text,text,bigint,text,date,text) returns bigint as 
 $$
 declare
 cod bigint;
 begin  
  insert into movimientos(codtipomovimiento,naturaleza,codusuario,codtiporesponsable,identificacion,codmovimiento,concepto,fechamovimiento,totalmovimiento,idperiodo)  
  values ($1,$2,$3,$4,$5,$6,$7,$8,0,0);   
  return cod;   
 end
 $$language plpgsql;

--------------------------------------------------------------------------------------------------------------------------------

--------------------------------------------------------------------------------------------------------------------------------

create or replace function insertarDetalle(bigint, text, int, float, float,text,text,text) returns void as 
$$
 declare
  stoc float;
  costm  float;  
  totalm float;
  totalf float;
  preciou float;
  stockt float;  
  nat text;
  tipomov text;

  cod bigint;
  cod2 bigint;
  pevp float;
 begin  
   select codtipomovimiento, naturaleza into tipomov,nat from movimientos where codmovimiento=$1;
  
  -- select costo, stock from articulos where codarticulo='000001';
   select costo, stock into costm, stoc from articulos where codarticulo=$2;

    
  if tipomov = 'DPC' OR tipomov ='DPV' THEN 
   $4=$4*-1;   
  end if;  

  totalf = costm * stoc;
  totalm = $4 * $5;

  if nat = 'I' then
    totalf=totalf+totalm;
    stockt=stoc+$4;
  else
   totalf=totalf-totalm;
   stockt=stoc-$4;     
  end if;

if stockt>0 then
  preciou=totalf/stockt;
 else
  preciou=totalf/1;
end if;
 INSERT INTO detallemovimiento(codmovimiento, codarticulo, orden, cantidad, costo, costotal, cantidadTotal, costounitariototal)
    VALUES ($1, $2, $3, $4, $5, totalf,stockt, preciou);  
    --raise exception 'costo % ',$5;



 
  

 update articulos set costo=preciou, stock=stockt where codarticulo=$2;    
 end
$$
language plpgsql;

 

--DELECT FROM detallemovimiento
--SELECT  insertarDetalle(2, '000001', 1, 2, 4.5) ;


--------------------------------------------------------------------------------------------------------------------------





--------------------------------------------------------------------------------------------------------------------------












 




insert into provincia values('02','BOLIVAR');
insert into provincia values('03','CAÑAR');
insert into provincia values('04','CARCHI');
insert into provincia values('05','COTOPAXI');
insert into provincia values('06','CHIMBORAZO');
insert into provincia values('07','EL ORO');
insert into provincia values('08','ESMERALDAS');
insert into provincia values('09','GUAYAS');
insert into provincia values('10','IMBABURA');
insert into provincia values('11','LOJA');
insert into provincia values('13','MANABI');
insert into provincia values('14','MORONA SANTIAGO');
insert into provincia values('15','NAPO');
insert into provincia values('16','PASTAZA');
insert into provincia values('17','PICHINCHA');
insert into provincia values('18','TUNGURAHUA');
insert into provincia values('19','ZAMORA CHINCHIPE');
insert into provincia values('20','GALAPAGOS');
insert into provincia values('21','SUCUMBIOS');
insert into provincia values('22','ORELLANA');
insert into provincia values('23','SANTO DOMINGO DE LOS TSACHILAS');
insert into provincia values('24','SANTA ELENA');
insert into provincia values('90','ZONAS NO DELIMITADAS');

----------------------------------------------------------------------------

insert into ciudades values('0102','01','GIRÓN');
insert into ciudades values('0103','01','GUALACEO');
insert into ciudades values('0104','01','NABÓN');
insert into ciudades values('0105','01','PAUTE');
insert into ciudades values('0106','01','PUCARA');
insert into ciudades values('0107','01','SAN FERNANDO');
insert into ciudades values('0108','01','SANTA ISABEL');
insert into ciudades values('0109','01','SIGSIG');
insert into ciudades values('0110','01','OÑA');
insert into ciudades values('0111','01','CHORDELEG');
insert into ciudades values('0112','01','EL PAN');
insert into ciudades values('0113','01','SEVILLA DE ORO');
insert into ciudades values('0114','01','GUACHAPALA');
insert into ciudades values('0115','01','CAMILO PONCE ENRÍQUEZ');
insert into ciudades values('0201','02','GUARANDA');
insert into ciudades values('0202','02','CHILLANES');
insert into ciudades values('0203','02','CHIMBO');
insert into ciudades values('0204','02','ECHEANDÍA');
insert into ciudades values('0205','02','SAN MIGUEL');
insert into ciudades values('0206','02','CALUMA');
insert into ciudades values('0207','02','LAS NAVES');
insert into ciudades values('0301','03','AZOGUES');
insert into ciudades values('0302','03','BIBLIÁN');
insert into ciudades values('0303','03','CAÑAR');
insert into ciudades values('0304','03','LA TRONCAL');
insert into ciudades values('0305','03','EL TAMBO');
insert into ciudades values('0306','03','DÉLEG');
insert into ciudades values('0307','03','SUSCAL');
insert into ciudades values('0401','04','TULCÁN');
insert into ciudades values('0402','04','BOLÍVAR');
insert into ciudades values('0403','04','ESPEJO');
insert into ciudades values('0404','04','MIRA');
insert into ciudades values('0405','04','MONTÚFAR');
insert into ciudades values('0406','04','SAN PEDRO DE HUACA');
insert into ciudades values('0501','05','LATACUNGA');
insert into ciudades values('0502','05','LA MANÁ');
insert into ciudades values('0503','05','PANGUA');
insert into ciudades values('0504','05','PUJILI');
insert into ciudades values('0505','05','SALCEDO');
insert into ciudades values('0506','05','SAQUISILÍ');
insert into ciudades values('0507','05','SIGCHOS');
insert into ciudades values('0601','06','RIOBAMBA');
insert into ciudades values('0602','06','ALAUSI');
insert into ciudades values('0603','06','COLTA');
insert into ciudades values('0604','06','CHAMBO');
insert into ciudades values('0605','06','CHUNCHI');
insert into ciudades values('0606','06','GUAMOTE');
insert into ciudades values('0607','06','GUANO');
insert into ciudades values('0608','06','PALLATANGA');
insert into ciudades values('0609','06','PENIPE');
insert into ciudades values('0610','06','CUMANDÁ');
insert into ciudades values('0701','07','MACHALA');
insert into ciudades values('0702','07','ARENILLAS');
insert into ciudades values('0703','07','ATAHUALPA');
insert into ciudades values('0704','07','BALSAS');
insert into ciudades values('0705','07','CHILLA');
insert into ciudades values('0706','07','EL GUABO');
insert into ciudades values('0707','07','HUAQUILLAS');
insert into ciudades values('0708','07','MARCABELÍ');
insert into ciudades values('0709','07','PASAJE');
insert into ciudades values('0710','07','PIÑAS');
insert into ciudades values('0711','07','PORTOVELO');
insert into ciudades values('0712','07','SANTA ROSA');
insert into ciudades values('0713','07','ZARUMA');
insert into ciudades values('0714','07','LAS LAJAS');
insert into ciudades values('0801','08','ESMERALDAS');
insert into ciudades values('0802','08','ELOY ALFARO');
insert into ciudades values('0803','08','MUISNE');
insert into ciudades values('0804','08','QUININDÉ');
insert into ciudades values('0805','08','SAN LORENZO');
insert into ciudades values('0806','08','ATACAMES');
insert into ciudades values('0807','08','RIOVERDE');
insert into ciudades values('0808','08','LA CONCORDIA');
insert into ciudades values('0901','09','GUAYAQUIL');
insert into ciudades values('0902','09','ALFREDO BAQUERIZO MORENO (JUJÁN)');
insert into ciudades values('0903','09','BALAO');
insert into ciudades values('0904','09','BALZAR');
insert into ciudades values('0905','09','COLIMES');
insert into ciudades values('0906','09','DAULE');
insert into ciudades values('0907','09','DURÁN');
insert into ciudades values('0908','09','EL EMPALME');
insert into ciudades values('0909','09','EL TRIUNFO');
insert into ciudades values('0910','09','MILAGRO');
insert into ciudades values('0911','09','NARANJAL');
insert into ciudades values('0912','09','NARANJITO');
insert into ciudades values('0913','09','PALESTINA');
insert into ciudades values('0914','09','PEDRO CARBO');
insert into ciudades values('0916','09','SAMBORONDÓN');
insert into ciudades values('0918','09','SANTA LUCÍA');
insert into ciudades values('0919','09','SALITRE (URBINA JADO)');
insert into ciudades values('0920','09','SAN JACINTO DE YAGUACHI');
insert into ciudades values('0921','09','PLAYAS');
insert into ciudades values('0922','09','SIMÓN BOLÍVAR');
insert into ciudades values('0923','09','CORONEL MARCELINO MARIDUEÑA');
insert into ciudades values('0924','09','LOMAS DE SARGENTILLO');
insert into ciudades values('0925','09','NOBOL');
insert into ciudades values('0927','09','GENERAL ANTONIO ELIZALDE');
insert into ciudades values('0928','09','ISIDRO AYORA');
insert into ciudades values('1001','10','IBARRA');
insert into ciudades values('1002','10','ANTONIO ANTE');
insert into ciudades values('1003','10','COTACACHI');
insert into ciudades values('1004','10','OTAVALO');
insert into ciudades values('1005','10','PIMAMPIRO');
insert into ciudades values('1006','10','SAN MIGUEL DE URCUQUÍ');
insert into ciudades values('1101','11','LOJA');
insert into ciudades values('1102','11','CALVAS');
insert into ciudades values('1103','11','CATAMAYO');
insert into ciudades values('1104','11','CELICA');
insert into ciudades values('1105','11','CHAGUARPAMBA');
insert into ciudades values('1106','11','ESPÍNDOLA');
insert into ciudades values('1107','11','GONZANAMÁ');
insert into ciudades values('1108','11','MACARÁ');
insert into ciudades values('1109','11','PALTAS');
insert into ciudades values('1110','11','PUYANGO');
insert into ciudades values('1111','11','SARAGURO');
insert into ciudades values('1112','11','SOZORANGA');
insert into ciudades values('1113','11','ZAPOTILLO');
insert into ciudades values('1114','11','PINDAL');
insert into ciudades values('1115','11','QUILANGA');
insert into ciudades values('1116','11','OLMEDO');

insert into ciudades values('1202','12','BABA');
insert into ciudades values('1203','12','MONTALVO');
insert into ciudades values('1204','12','PUEBLOVIEJO');
insert into ciudades values('1205','12','QUEVEDO');
insert into ciudades values('1206','12','URDANETA');
insert into ciudades values('1207','12','VENTANAS');
insert into ciudades values('1208','12','VINCES');
insert into ciudades values('1209','12','PALENQUE');
insert into ciudades values('1210','12','BUENA FÉ');
insert into ciudades values('1211','12','VALENCIA');
insert into ciudades values('1212','12','MOCACHE');
insert into ciudades values('1213','12','QUINSALOMA');
insert into ciudades values('1301','13','PORTOVIEJO');
insert into ciudades values('1302','13','BOLÍVAR');
insert into ciudades values('1303','13','CHONE');
insert into ciudades values('1304','13','EL CARMEN');
insert into ciudades values('1305','13','FLAVIO ALFARO');
insert into ciudades values('1306','13','JIPIJAPA');
insert into ciudades values('1307','13','JUNÍN');
insert into ciudades values('1308','13','MANTA');
insert into ciudades values('1309','13','MONTECRISTI');
insert into ciudades values('1310','13','PAJÁN');
insert into ciudades values('1311','13','PICHINCHA');
insert into ciudades values('1312','13','ROCAFUERTE');
insert into ciudades values('1313','13','SANTA ANA');
insert into ciudades values('1314','13','SUCRE');
insert into ciudades values('1315','13','TOSAGUA');
insert into ciudades values('1316','13','24 DE MAYO');
insert into ciudades values('1317','13','PEDERNALES');
insert into ciudades values('1318','13','OLMEDO');
insert into ciudades values('1319','13','PUERTO LÓPEZ');
insert into ciudades values('1320','13','JAMA');
insert into ciudades values('1321','13','JARAMIJÓ');
insert into ciudades values('1322','13','SAN VICENTE');
insert into ciudades values('1401','14','MORONA');
insert into ciudades values('1402','14','GUALAQUIZA');
insert into ciudades values('1403','14','LIMÓN INDANZA');
insert into ciudades values('1404','14','PALORA');
insert into ciudades values('1405','14','SANTIAGO');
insert into ciudades values('1406','14','SUCÚA');
insert into ciudades values('1407','14','HUAMBOYA');
insert into ciudades values('1408','14','SAN JUAN BOSCO');
insert into ciudades values('1409','14','TAISHA');
insert into ciudades values('1410','14','LOGROÑO');
insert into ciudades values('1411','14','PABLO SEXTO');
insert into ciudades values('1412','14','TIWINTZA');
insert into ciudades values('1501','15','TENA');
insert into ciudades values('1503','15','ARCHIDONA');
insert into ciudades values('1504','15','EL CHACO');
insert into ciudades values('1507','15','QUIJOS');
insert into ciudades values('1509','15','CARLOS JULIO AROSEMENA TOLA');
insert into ciudades values('1601','16','PASTAZA');
insert into ciudades values('1602','16','MERA');
insert into ciudades values('1603','16','SANTA CLARA');
insert into ciudades values('1604','16','ARAJUNO');
insert into ciudades values('1701','17','QUITO');
insert into ciudades values('1702','17','CAYAMBE');
insert into ciudades values('1703','17','MEJIA');
insert into ciudades values('1704','17','PEDRO MONCAYO');
insert into ciudades values('1705','17','RUMIÑAHUI');
insert into ciudades values('1707','17','SAN MIGUEL DE LOS BANCOS');
insert into ciudades values('1708','17','PEDRO VICENTE MALDONADO');
insert into ciudades values('1709','17','PUERTO QUITO');
insert into ciudades values('1801','18','AMBATO');
insert into ciudades values('1802','18','BAÑOS DE AGUA SANTA');
insert into ciudades values('1803','18','CEVALLOS');
insert into ciudades values('1804','18','MOCHA');
insert into ciudades values('1805','18','PATATE');
insert into ciudades values('1806','18','QUERO');
insert into ciudades values('1807','18','SAN PEDRO DE PELILEO');
insert into ciudades values('1808','18','SANTIAGO DE PÍLLARO');
insert into ciudades values('1809','18','TISALEO');
insert into ciudades values('1901','19','ZAMORA');
insert into ciudades values('1902','19','CHINCHIPE');
insert into ciudades values('1903','19','NANGARITZA');
insert into ciudades values('1904','19','YACUAMBI');
insert into ciudades values('1905','19','YANTZAZA (YANZATZA)');
insert into ciudades values('1906','19','EL PANGUI');
insert into ciudades values('1907','19','CENTINELA DEL CÓNDOR');
insert into ciudades values('1908','19','PALANDA');
insert into ciudades values('1909','19','PAQUISHA');
insert into ciudades values('2001','20','SAN CRISTÓBAL');
insert into ciudades values('2002','20','ISABELA');
insert into ciudades values('2003','20','SANTA CRUZ');
insert into ciudades values('2101','21','LAGO AGRIO');
insert into ciudades values('2102','21','GONZALO PIZARRO');
insert into ciudades values('2103','21','PUTUMAYO');
insert into ciudades values('2104','21','SHUSHUFINDI');
insert into ciudades values('2105','21','SUCUMBÍOS');
insert into ciudades values('2106','21','CASCALES');
insert into ciudades values('2107','21','CUYABENO');
insert into ciudades values('2201','22','ORELLANA');
insert into ciudades values('2202','22','AGUARICO');
insert into ciudades values('2203','22','LA JOYA DE LOS SACHAS');
insert into ciudades values('2204','22','LORETO');
insert into ciudades values('2301','23','SANTO DOMINGO');
insert into ciudades values('2401','24','SANTA ELENA');
insert into ciudades values('2402','24','LA LIBERTAD');
insert into ciudades values('2403','24','SALINAS');
insert into ciudades values('9001','90','LAS GOLONDRINAS');
insert into ciudades values('9003','90','MANGA DEL CURA');
insert into ciudades values('9004','90','EL PIEDRERO');

---------------------------------------------------------------------

