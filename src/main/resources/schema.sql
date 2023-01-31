CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- schema.sql
CREATE TABLE IF NOT EXISTS WHISKY (
    id UUID NOT NULL DEFAULT uuid_generate_v4(),
    bottle VARCHAR(255) NOT NULL,
    price VARCHAR(255) NOT NULL,
    rating VARCHAR(255) NOT NULL,
    region VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);
