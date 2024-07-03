DROP SCHEMA IF EXISTS professor_trainer_schema CASCADE;

CREATE SCHEMA professor_trainer_schema;

SET search_path TO professor_trainer_schema;

DROP TABLE IF EXISTS professors;

CREATE TABLE professors( 
	p_id SERIAL PRIMARY KEY,
	username VARCHAR(50) DEFAULT NULL,
	password VARCHAR(50) DEFAULT NULL
);

INSERT INTO professors (p_id, username, password) VALUES
(DEFAULT, 'prof_oak', 'password1'),
(DEFAULT, 'prof_elm', 'password2'),
(DEFAULT, 'prof_birch', 'password3'),
(DEFAULT, 'prof_rowan', 'password4'),
(DEFAULT, 'prof_kukui', 'password5');

DROP TABLE IF EXISTS trainers;

CREATE TABLE trainers( 
	t_id SERIAL PRIMARY KEY, 
	first_name VARCHAR(45) DEFAULT NULL,
	last_name VARCHAR(45) DEFAULT NULL,
	email VARCHAR(45) UNIQUE DEFAULT NULL,
	regional_professor_id INT,
	FOREIGN KEY (regional_professor_id) REFERENCES professors(p_id) ON DELETE SET NULL
);

INSERT INTO trainers (t_id, first_name, last_name, email, regional_professor_id) VALUES
(DEFAULT, 'Ash', 'Ketchum', 'ash.ketchum@email.com', 1),
(DEFAULT, 'Gold', 'Silver', 'gold.silver@email.com', 2),
(DEFAULT, 'Misty', 'Waterflower', 'misty.waterflower@email.com', 3),
(DEFAULT, 'Ruby', 'Sapphire', 'ruby.sapphire@email.com', 4),
(DEFAULT, 'Dawn', 'Diamond', 'dawn.diamond@email.com', 5);