--liquibase formatted sql


-- Schema

--changeset KT:1
create schema contacts;
--rollback drop schema contacts;

-- Tables

--changeset KT:2
CREATE TABLE contacts.contact
(
    id                 SERIAL PRIMARY KEY ,
    name                VARCHAR(255) NOT NULL,
    code_name           VARCHAR(255) NOT NULL,
    phone_number           VARCHAR(255) NOT NULL,
    object_status       text NOT NULL DEFAULT 'ACTIVE'::text
);
--rollback drop table contacts.contact;
