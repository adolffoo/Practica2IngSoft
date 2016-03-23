CREATE SEQUENCE incremento
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;

COMMENT ON SEQUENCE incremento IS 'Incremento para los ids de los usuarios';


CREATE TABLE usuario
(
  usuario character varying(50) NOT NULL,
  contrasenia character varying(50),
  "idUsuario" integer NOT NULL DEFAULT nextval('incremento'::regclass),
  CONSTRAINT usuario_pkey PRIMARY KEY ("idUsuario")
)
WITH (
  OIDS=FALSE
);


COMMENT ON TABLE usuario IS 'Tabla que guarda los usuaios y sus contrasenias';



CREATE TABLE info
(
  correo character varying(50) NOT NULL,
  nombre character varying(50),
  apellido character varying(50),
  "idUsuario" integer,
  "IdInfo" integer NOT NULL,
  CONSTRAINT "informacionUsuario_pkey" PRIMARY KEY ("IdInfo"),
  CONSTRAINT "info_idUsuario_fkey" FOREIGN KEY ("idUsuario")
      REFERENCES usuario ("idUsuario") MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);

COMMENT ON TABLE usuario IS 'Tabla que guarda la informacion de cada usuario';
