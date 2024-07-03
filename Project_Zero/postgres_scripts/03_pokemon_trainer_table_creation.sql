DROP SCHEMA IF EXISTS pokemon_trainer_schema CASCADE;

CREATE SCHEMA trainer_schema;

SET search_path TO trainer_schema;

DROP TABLE IF EXISTS trainer;

CREATE TABLE trainer( 
	t_id SERIAL PRIMARY KEY, 
	first_name VARCHAR(45) DEFAULT NULL,
	last_name VARCHAR(45) DEFAULT NULL,
	email VARCHAR(45) UNIQUE DEFAULT NULL
);

INSERT INTO trainer (t_id, first_name, last_name, email) VALUES
(DEFAULT, 'Ash', 'Ketchum', 'ash.ketchum@email.com'),
(DEFAULT, 'Gold', 'Silver', 'gold.silver@email.com'),
(DEFAULT, 'Misty', 'Waterflower', 'misty.waterflower@email.com'),
(DEFAULT, 'Ruby', 'Sapphire', 'ruby.sapphire@email.com'),
(DEFAULT, 'Dawn', 'Diamond', 'dawn.diamond@email.com'),
(DEFAULT, 'Brock', 'Harrison', 'brock.harrison@email.com');