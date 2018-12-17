--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.15
-- Dumped by pg_dump version 11.1 (Ubuntu 11.1-1.pgdg16.04+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: categoria; Type: TABLE; Schema: public; Owner: postgres
--

CREATE SCHEMA IF NOT EXISTS public;

CREATE TABLE IF NOT EXISTS public.categoria (
    codigo bigint NOT NULL,
    nome character varying(255)
);


ALTER TABLE public.categoria OWNER TO postgres;

--
-- Name: categoria_codigo_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.categoria_codigo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.categoria_codigo_seq OWNER TO postgres;

--
-- Name: categoria_codigo_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.categoria_codigo_seq OWNED BY public.categoria.codigo;


--
--
-- Name: categoria codigo; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.categoria ALTER COLUMN codigo SET DEFAULT nextval('public.categoria_codigo_seq'::regclass);


--
-- Data for Name: categoria; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.categoria (codigo, nome) FROM stdin;
\.


--
-- Data for Name: flyway_schema_history; Type: TABLE DATA; Schema: public; Owner: postgres
--

--
-- Name: categoria_codigo_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.categoria_codigo_seq', 1, false);


--
-- Name: categoria categoria_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.categoria
    ADD CONSTRAINT categoria_pkey PRIMARY KEY (codigo);


--
-- Name: flyway_schema_history flyway_schema_history_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--
--
-- Name: flyway_schema_history_s_idx; Type: INDEX; Schema: public; Owner: postgres
--


--
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


INSERT INTO public.categoria(nome) VALUES ('Lazer');
INSERT INTO public.categoria(nome) VALUES ('Alimentação');
INSERT INTO public.categoria(nome) VALUES ('Supermercado');
INSERT INTO public.categoria(nome) VALUES ('Farmácia');
INSERT INTO public.categoria(nome) VALUES ('Outros');


--
-- PostgreSQL database dump complete
--
