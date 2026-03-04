--
-- PostgreSQL database dump
--

\restrict FeDNNwcxv7F21AHpoGesgHdpGIbULqQwAb5znfDck9YFlEs7ks4FY6slk6XXhGk

-- Dumped from database version 18.2 (Homebrew)
-- Dumped by pg_dump version 18.2 (Homebrew)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

ALTER TABLE IF EXISTS ONLY public.products DROP CONSTRAINT IF EXISTS products_pkey;
ALTER TABLE IF EXISTS public.products ALTER COLUMN id DROP DEFAULT;
DROP SEQUENCE IF EXISTS public.products_id_seq;
DROP TABLE IF EXISTS public.products;
SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: products; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.products (
    id integer NOT NULL,
    name character varying(100) NOT NULL,
    unit_price numeric(10,2) NOT NULL,
    qty integer NOT NULL,
    import_date date DEFAULT CURRENT_DATE
);


ALTER TABLE public.products OWNER TO postgres;

--
-- Name: products_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.products_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.products_id_seq OWNER TO postgres;

--
-- Name: products_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.products_id_seq OWNED BY public.products.id;


--
-- Name: products id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.products ALTER COLUMN id SET DEFAULT nextval('public.products_id_seq'::regclass);


--
-- Data for Name: products; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.products (id, name, unit_price, qty, import_date) FROM stdin;
1	Laptop	1200.00	10	2026-03-01
2	Mouse	25.50	50	2026-03-02
3	Keyboard	45.99	30	2026-03-02
4	Monitor	250.75	15	2026-03-03
5	Printer	180.40	8	2026-03-01
6	USB Cable	8.99	100	2026-03-03
7	External HDD	95.00	20	2026-03-02
8	Webcam	60.00	25	2026-03-01
9	Headphones	75.25	18	2026-03-03
10	Speaker	110.00	12	2026-03-04
\.


--
-- Name: products_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.products_id_seq', 10, true);


--
-- Name: products products_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_pkey PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--

\unrestrict FeDNNwcxv7F21AHpoGesgHdpGIbULqQwAb5znfDck9YFlEs7ks4FY6slk6XXhGk

