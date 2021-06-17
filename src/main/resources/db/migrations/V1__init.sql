-- // create test_user
-- Migration SQL that makes the change goes here.

CREATE TABLE test_user (
     id  UUID NOT NULL UNIQUE,
     email VARCHAR(255) NOT NULL UNIQUE,
     license_type VARCHAR(255) NOT NULL,
     reason VARCHAR(255) NOT NULL,
     created_at TIMESTAMP NOT NULL

);




