
DROP SCHEMA IF EXISTS "appointment" CASCADE;

CREATE SCHEMA "appointment";

DROP TYPE IF EXISTS outbox_status;
CREATE TYPE outbox_status AS ENUM('STARTED', 'COMPLETED', 'FAILED');

DROP TYPE IF EXISTS appointment_status;
CREATE TYPE appointment_status AS ENUM('REQUESTING', 'CONFIRMED', 'CANCELLED', 'CANCELLING', 'PAID');

DROP TYPE IF EXISTS payment_status;
CREATE TYPE payment_status AS ENUM('PENDING', 'COMPLETED', 'FAILED');

DROP TABLE IF EXISTS "appointment".appointments CASCADE;

CREATE TABLE "appointment".appointments(
    id uuid NOT NULL,
    owner_id uuid NOT NULL,
    pet_id uuid NOT NULL,
    description varchar NOT NULL,
    appointment_start_date_time timestamp WITHOUT TIME ZONE NOT NULL,
    appointment_end_date_time timestamp WITHOUT TIME ZONE NOT NULL,
    appointment_status appointment_status NOT NULL,
    payment_status payment_status NOT NULL,
    CONSTRAINT appointment_pkey PRIMARY KEY (id)
);

DROP TABLE IF EXISTS "appointment".accounts CASCADE;

CREATE TABLE "appointment".accounts(
    id uuid NOT NULL,
    email varchar NOT NULL,
    first_name varchar NOT NULL,
    last_name varchar NOT NULL,
    CONSTRAINT account_pkey PRIMARY KEY (id)
);

DROP TABLE IF EXISTS "appointment".pets CASCADE;

CREATE TABLE "appointment".pets (
    id uuid NOT NULL,
    owner_id uuid NOT NULL,
    name varchar NOT NULL,
    species varchar NOT NULL,
    birth_date date NOT NULL,
    CONSTRAINT pet_pkey PRIMARY KEY (id)
);

DROP TABLE IF EXISTS "appointment".availability_outbox CASCADE;

CREATE TABLE "appointment".availability_outbox (
    id uuid NOT NULL,
    created_at TIMESTAMP NOT NULL,
    processed_at TIMESTAMP,
    payload jsonb NOT NULL,
    outbox_status outbox_status NOT NULL,
    version integer NOT NULL,
    CONSTRAINT availability_outbox_pkey PRIMARY KEY (id)
);