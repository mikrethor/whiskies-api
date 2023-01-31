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

-- INSERT INTO WHISKY (id, bottle, price, rating, region) VALUES ('9376cfa9-b7f2-4519-82b7-8f3b9248ef24', 'Lagavulin 1995 Distillers Edition','101.085','88.9090909090909','Islay');