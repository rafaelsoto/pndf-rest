CREATE TABLE public.percurso
(
   id serial, 
   titulo character varying, 
   nome character varying, 
   distancia character varying,
   percurso character varying,
   diaSemana character varying,
   mapa character varying, 
   data timestamp without time zone, 
   texto text, 
   CONSTRAINT percurso_pk PRIMARY KEY (id)
) 
WITH (
  OIDS = FALSE
)


INSERT INTO public.percurso(
             titulo, nome, distancia, percurso, diasemana, mapa, data, 
            texto)
    VALUES ('Teste', 'Teste', '20KM', 'PARQUE, L4', 'Monday', 'http://dsdasdas.com', '2016-09-23', 'dasdasda dasdnaslkdmasd aslkdmasldmas');
