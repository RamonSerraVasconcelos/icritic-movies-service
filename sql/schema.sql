CREATE TABLE categories (
    id BIGSERIAL NOT NULL,
    name VARCHAR(75),
    description VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE directors (
    id BIGSERIAL NOT NULL,
    name VARCHAR(75),
    description VARCHAR(255),
    country_id BIGSERIAL,
    PRIMARY KEY (id)
);