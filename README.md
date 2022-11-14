# GBIF-API-Tools

This app provides Web-UI-based tools to view and request data from the [Global Biodiversity
Information Facility](https://www.gbif.org/). It is a personal project and not affiliated to
GBIF. Feel free to send PRs and ideas.

## Tools

### Backbone Tree

This feature will gather the Taxonomy Backbone into a separate local dataset to make it
accessible in a clickable tree-shaped UI. The nodes will provide links back to direct GBIF-API
requests or the GBIF-UI.

## Development, Building and Deployment Notes

### Docker Images

#### Backend

The Project uses a gradle base image for the backend to have self-contained builds and speedier
build-debugging. Keep versions in sync with wrapper to avoid problems.

#### Database

The Postgres database can be built from the included "Dockerfile.database". The **numbered shell/SQL
scripts** (numbers determining execution order) in `src/main/resources/database` must then be copied
into `/docker-entrypoint-initdb.d` of the image for initialisation. Note that Postgres will only
read the scripts as long as the `PGDATA` location is empty. This is also the reason for the config
to first be copied to `/etc/postgres`and then to PGDATA by an init script.

The config examples etc. of the container are at `/usr/share/postgresql`.
The running configs of the container are at `/var/lib/postgresql/data` (PGDATA).

### Spring Configuration

#### Profiles

The base profiles **development** and **production** are set by the environment
variable `SPRING_PROFILES_ACTIVE`. Either in the IDEA-run-configuration, `docker-compose.yaml` or
the docker image. **development** has to be used together with either **local** or **local-docker**
or **external** to add the appropriate datasources. Also `SPRING_CONFIG_LOCATION` fixes the config
location in development for faster startup.

### Database Model and Entities

As backend and database are separated into different modules/images and Gradle is only set up
for the backend the auto-generated SQL-schema has to be updated manually from
`backend/src/main/resources/database/schema-generated.sql` to `database/init/02-schema-generated.
sql`.

### CI

CI is configured for Github Actions and runs everything in containers which are pushed to Docker
Hub. Note that the CI job-container for testing is not using the Dockerfile but must be separately
configured to use the same image version. The gradle build-scan results are available as a
ZIP-archive in the workflow and the link to the report page on Gradle Enterprise is printed into the
logs as a job-step.

### VS Code

VS Code workspaces are ignored by git because I use it mainly with the SQL-Tools extension which
saves credentials in it.

### Snippets

#### Database Insert

```
insert into taxon (id, authorship, extinct, name_canonical, num_descendants, num_occurrences, path) 
  values (1, 'Me', false, 'Cocos nucifera', 1, 1, '{"path", "to", "taxon"}');
```

### Issues and Bugs

- Failing controller-test for `FindTaxon` query while working fine when executed
  manually [(see Stackoverflow)](https://stackoverflow.com/questions/74460453/why-is-my-data-not-persisted-accessible-in-an-spring-boot-integration-test-with)