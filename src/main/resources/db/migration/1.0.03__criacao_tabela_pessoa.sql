CREATE SEQUENCE public.pessoa_codigo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE public.pessoa
(
  codigo bigint NOT NULL DEFAULT nextval('pessoa_codigo_seq'::regclass),
  ativo boolean NOT NULL,
  bairro character varying(255),
  cep character varying(255),
  cidade character varying(255),
  complemento character varying(255),
  estado character varying(255),
  logradouro character varying(255),
  numero character varying(255),
  nome character varying(255) NOT NULL,
  CONSTRAINT pessoa_pkey PRIMARY KEY (codigo)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.pessoa
  OWNER TO postgres;