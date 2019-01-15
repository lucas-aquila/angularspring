--Tabela de Usuário
CREATE TABLE public.usuario
(
  codigo bigint NOT NULL,
  email character varying(255),
  nome character varying(255),
  senha character varying(255),
  CONSTRAINT usuario_pkey PRIMARY KEY (codigo)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.usuario
  OWNER TO postgres;

-- Tabela de Permissão
CREATE TABLE public.permissao
(
  codigo bigint NOT NULL,
  descricao character varying(255),
  CONSTRAINT permissao_pkey PRIMARY KEY (codigo)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.permissao
  OWNER TO postgres;

-- Tabela associativa entre usuário e permissão  
CREATE TABLE public.usuario_permissao
(
  codigo_usuario bigint NOT NULL,
  codigo_permissao bigint NOT NULL,
  CONSTRAINT fk5tjrvuwlx1yp72mrf8t8vj93e FOREIGN KEY (codigo_permissao)
      REFERENCES public.permissao (codigo) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fkeogfr4akeqn19xr3wmyx0n8bo FOREIGN KEY (codigo_usuario)
      REFERENCES public.usuario (codigo) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.usuario_permissao
  OWNER TO postgres;