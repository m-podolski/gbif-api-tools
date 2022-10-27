FROM gradle:7.4.1-jdk17-alpine as development
# Synchronize gradle image version with wrapper!

WORKDIR /app
EXPOSE 8080
ENTRYPOINT ["gradle", "bootRun"]

FROM development as build

WORKDIR /app
COPY .. .
# Using "assemble" instead of "build" to test before building in CI
RUN gradle assemble

ARG EXTRACTED=build/extracted
RUN mkdir /app/$EXTRACTED && \
  java -Djarmode=layertools \
    -jar build/libs/*.jar extract \
    --destination $EXTRACTED

ENTRYPOINT ["/bin/sh"]


FROM eclipse-temurin:17.0.4.1_1-jre-alpine as production

WORKDIR /app
ARG EXTRACTED=/app/build/extracted
COPY --from=build $EXTRACTED/dependencies/ ./
COPY --from=build $EXTRACTED/spring-boot-loader/ ./
COPY --from=build $EXTRACTED/snapshot-dependencies/ ./
COPY --from=build $EXTRACTED/application/ ./

ARG USER=gat
ARG GROUP=gatgroup
RUN addgroup $GROUP && \
  adduser $USER $GROUP && \
  chown -R $USER:$GROUP /app
USER $USER

ENV SPRING_PROFILES_ACTIVE="production"
COPY src/main/resources/production.yaml ./

EXPOSE 8080
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
