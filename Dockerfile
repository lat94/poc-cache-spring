FROM maven:3.8.5-openjdk-17-slim AS build

WORKDIR /workspace/app

ARG USER
ARG PASSWORD

ENV GITHUB_USER=${GIT_USERNAME}
ENV GITHUB_TOKEN=${GIT_TOKEN}

COPY mvnw .
COPY pom.xml .
COPY src src

RUN mkdir -p ~/.m2
RUN echo "<settings><servers><server><id>github</id><username>$GITHUB_USER</username><password>$GITHUB_TOKEN</password></server></servers></settings>" > ~/.m2/settings.xml

RUN mvn package -DskipTests

FROM maven:3.8.5-openjdk-17-slim
VOLUME /tmp
COPY --from=build /workspace/app/target/poc-cache-spring.jar app.jar

ENV PROFILE=prod

ENTRYPOINT java -Djava.security.egd=file:/dev/./urandom -jar -XX:MinRAMPercentage=60 -XX:MaxRAMPercentage=80 -server -XX:+OptimizeStringConcat -XX:+UseStringDeduplication -Dspring.profiles.active=${PROFILE} /app.jar
