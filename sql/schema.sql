CREATE TABLE categories
(
    id          BIGSERIAL NOT NULL,
    name        VARCHAR(75),
    description VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE directors
(
    id          BIGSERIAL NOT NULL,
    name        VARCHAR(75),
    description VARCHAR(255),
    country_id  BIGSERIAL,
    PRIMARY KEY (id)
);

CREATE TABLE actors
(
    id          BIGSERIAL NOT NULL,
    name        VARCHAR(75),
    description VARCHAR(255),
    country_id  BIGSERIAL,
    PRIMARY KEY (id)
);

CREATE TABLE movies
(
    id          BIGSERIAL NOT NULL,
    name        VARCHAR(75),
    synopsis    VARCHAR(500),
    country_id  INT8,
    rating      INT8,
    release_date DATE,
    active      BOOLEAN,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE TABLE movies_directors
(
    movie_id    BIGSERIAL NOT NULL REFERENCES movies(id) ON DELETE CASCADE,
    director_id BIGSERIAL NOT NULL REFERENCES directors(id) ON DELETE CASCADE,
    CONSTRAINT movies_directors_pk PRIMARY KEY (movie_id, director_id)
);

CREATE TABLE movies_categories
(
    movie_id    BIGSERIAL NOT NULL REFERENCES movies(id) ON DELETE CASCADE,
    category_id BIGSERIAL NOT NULL REFERENCES categories(id) ON DELETE CASCADE,
    CONSTRAINT movies_categories_pk PRIMARY KEY (movie_id, category_id)
);

CREATE TABLE movies_actors
(
    movie_id BIGSERIAL NOT NULL REFERENCES movies(id) ON DELETE CASCADE,
    actor_id BIGSERIAL NOT NULL REFERENCES actors(id) ON DELETE CASCADE,
    CONSTRAINT movies_actors_pk PRIMARY KEY (movie_id, actor_id)
);

-- FUNCTION TO CREATE AND UPDATE DATES
CREATE OR REPLACE FUNCTION trigger_set_timestamp()
RETURNS TRIGGER AS
'
BEGIN
  NEW.updated_at = NOW();
  RETURN NEW;
END;
'
LANGUAGE plpgsql;

--TRIGGERS TO UPDATE DATES ON TABLES
CREATE TRIGGER set_timestamp
    BEFORE UPDATE ON movies
    FOR EACH ROW
    EXECUTE PROCEDURE trigger_set_timestamp();

