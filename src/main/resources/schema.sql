CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- schema.sql
CREATE TABLE IF NOT EXISTS ACTOR (
    id UUID NOT NULL DEFAULT uuid_generate_v4(),
    full_name VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS MOVIE (
    id UUID NOT NULL DEFAULT uuid_generate_v4(),
    title VARCHAR(255) NOT NULL,
    year smallint NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS ACTOR_MOVIE_MAPPING (
    actor_id UUID NOT NULL,
    movie_id UUID NOT NULL,
    CONSTRAINT fk_actor
    FOREIGN KEY(actor_id)
    REFERENCES ACTOR(id),
    CONSTRAINT fk_movie
    FOREIGN KEY(movie_id)
    REFERENCES MOVIE(id)
);
