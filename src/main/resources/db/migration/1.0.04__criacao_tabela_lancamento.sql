CREATE SEQUENCE public.lancamento_codigo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE public.lancamento
(
  codigo bigint NOT NULL DEFAULT nextval('lancamento_codigo_seq'::regclass),
  data_pagamento date,
  data_vencimento date,
  descricao character varying(255),
  observacao character varying(255),
  tipo character varying(255),
  valor numeric(19,2),
  codigo_categoria bigint,
  codigo_pessoa bigint,
  CONSTRAINT lancamento_pkey PRIMARY KEY (codigo),
  CONSTRAINT fk3oktdfripre9vo4ocu87op55a FOREIGN KEY (codigo_categoria)
      REFERENCES public.categoria (codigo) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk9pqx360616efx77k3w4j297yw FOREIGN KEY (codigo_pessoa)
      REFERENCES public.pessoa (codigo) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.lancamento
  OWNER TO postgres;