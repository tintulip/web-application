#!/bin/bash

DATABASE=$1
PGPASSWORD=postgres psql -U postgres -h localhost -c "drop database ${DATABASE}"
PGPASSWORD=postgres psql -U postgres -h localhost -c "create database ${DATABASE} OWNER = postgres"
