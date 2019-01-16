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
  
  
INSERT INTO public.permissao(codigo, descricao) VALUES (1, 'ROLE_CADASTRAR_CATEGORIA');
INSERT INTO public.permissao(codigo, descricao) VALUES (2, 'ROLE_PESQUISAR_CATEGORIA');
INSERT INTO public.permissao(codigo, descricao) VALUES (3, 'ROLE_CADASTRAR_PESSOA');
INSERT INTO public.permissao(codigo, descricao) VALUES (4, 'ROLE_REMOVER_PESSOA');
INSERT INTO public.permissao(codigo, descricao) VALUES (5, 'ROLE_PESQUISAR_PESSOA');
INSERT INTO public.permissao(codigo, descricao) VALUES (6, 'ROLE_CADASTRAR_LANCAMENTO');
INSERT INTO public.permissao(codigo, descricao) VALUES (7, 'ROLE_REMOVER_LANCAMENTO');
INSERT INTO public.permissao(codigo, descricao) VALUES (8, 'ROLE_PESQUISAR_LANCAMENTO');

INSERT INTO public.usuario(
            codigo, email, nome, senha)
    VALUES (1, 'admin@admin.com', 'Administrador', '$2a$10$MZ04or95aXpFKyAE6jm40.PRE9HybTKbDvE1P9NRY1X.N54l3eVuW');

INSERT INTO public.usuario(
            codigo, email, nome, senha)
    VALUES (2, 'usuario@usuario.com', 'Usuário', '$2a$10$4Tfek07DoSlLpD6vvTVt9eoVAhHIhkDB0nLYlH5HyIXrdXwrt9gI6');
    
INSERT INTO public.usuario_permissao(codigo_usuario, codigo_permissao) VALUES (1, 1);
INSERT INTO public.usuario_permissao(codigo_usuario, codigo_permissao) VALUES (1, 2);
INSERT INTO public.usuario_permissao(codigo_usuario, codigo_permissao) VALUES (1, 3);
INSERT INTO public.usuario_permissao(codigo_usuario, codigo_permissao) VALUES (1, 4);
INSERT INTO public.usuario_permissao(codigo_usuario, codigo_permissao) VALUES (1, 5);
INSERT INTO public.usuario_permissao(codigo_usuario, codigo_permissao) VALUES (1, 6);
INSERT INTO public.usuario_permissao(codigo_usuario, codigo_permissao) VALUES (1, 7);
INSERT INTO public.usuario_permissao(codigo_usuario, codigo_permissao) VALUES (1, 8);
INSERT INTO public.usuario_permissao(codigo_usuario, codigo_permissao) VALUES (2, 5);
INSERT INTO public.usuario_permissao(codigo_usuario, codigo_permissao) VALUES (2, 8);

