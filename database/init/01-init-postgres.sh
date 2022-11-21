#!/bin/bash
# This file has to be copied into /docker-entrypoint-initdb.d when building the image

set -e

# Config files are first copied into non-PGDATA-location to enable initd to run
mv /etc/postgresql/postgresql.conf /var/lib/postgresql/data

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
	CREATE ROLE admin WITH LOGIN PASSWORD '$DB_ROLE_ADMIN_PW';
	CREATE ROLE gat WITH LOGIN PASSWORD '$DB_ROLE_GAT_PW';

	CREATE DATABASE gat
	  WITH
	    OWNER gat
	    TEMPLATE template0
      LOCALE 'C';

	GRANT ALL PRIVILEGES ON DATABASE gat TO gat;
	GRANT ALL PRIVILEGES ON DATABASE gat TO admin;
EOSQL