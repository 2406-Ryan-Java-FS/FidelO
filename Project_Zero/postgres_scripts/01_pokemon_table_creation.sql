DROP SCHEMA IF EXISTS pokemons_schema CASCADE;

CREATE SCHEMA pokemons_schema;

SET search_path TO pokemons_schema;

DROP TABLE IF EXISTS pokemons;

-- I could make this a table and link it to my pokemon table
CREATE TABLE pokemons( 
	p_id SERIAL PRIMARY KEY, 
	primary_name VARCHAR(45) UNIQUE,
	primary_type VARCHAR(45) DEFAULT NULL,
	generation VARCHAR(45) DEFAULT NULL
);

INSERT INTO pokemons_schema.pokemons (p_id, primary_name, primary_type, generation) VALUES
(DEFAULT, 'Pikachu', 'Electric', 'I'),
(DEFAULT, 'Gastly', 'Ghost', 'I'),
(DEFAULT, 'Noctowl', 'Normal', 'II'),
(DEFAULT, 'Cranidos', 'Rock', 'IV');