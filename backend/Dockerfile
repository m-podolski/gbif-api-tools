FROM gradle:7.5.1-jdk17-jammy as development
# Synchronize gradle version in dockerfile, ci-workflow and wrapper!

WORKDIR /app
EXPOSE 8080
ENTRYPOINT ["gradle"]
CMD ["bootRun"]

FROM development as build

WORKDIR /app
COPY . .
# Using "assemble" instead of "build" to test before building in CI
RUN gradle assemble

ARG EXTRACTED=build/extracted
RUN mkdir /app/$EXTRACTED && \
  java -Djarmode=layertools \
    -jar build/libs/*.jar extract \
    --destination $EXTRACTED

ENTRYPOINT ["/bin/bash"]
CMD ["ls", "-la"]


FROM eclipse-temurin:17.0.4.1_1-jre-jammy as production

WORKDIR /app
ARG EXTRACTED=/app/build/extracted
COPY --from=build $EXTRACTED/dependencies/ ./
COPY --from=build $EXTRACTED/spring-boot-loader/ ./
COPY --from=build $EXTRACTED/snapshot-dependencies/ ./
COPY --from=build $EXTRACTED/application/ ./

ENV SPRING_PROFILES_ACTIVE="production"
COPY src/main/resources/production.yaml ./

WORKDIR /
ARG USER=gat
ARG GROUP=gatgroup
RUN --mount=type=secret,id=be_user_pw,required=true \
  useradd -m $USER -p $(cat /run/secrets/be_user_pw) && \
  groupadd $GROUP && \
  usermod -a -G $GROUP $USER
USER $USER

EXPOSE 8080
ENTRYPOINT ["java"]
CMD ["org.springframework.boot.loader.JarLauncher"]
