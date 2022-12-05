FROM postgres:15-bullseye as development

# Put config into non-PGDATA-location first to enable initd to run
# then copy config into PGDATA with init script
WORKDIR /etc/postgresql
COPY --chown=postgres:postgres config/postgresql.conf ./

WORKDIR /docker-entrypoint-initdb.d
COPY ./init/01-init-postgres.sh ./

EXPOSE 5432


FROM development as testing

WORKDIR /docker-entrypoint-initdb.d
COPY init/02-schema.sql ./

EXPOSE 5432
