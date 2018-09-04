FROM openjdk:8-jdk-alpine AS builder
ARG mavenUser
ARG mavenPass
WORKDIR /src
ADD . .
RUN mkdir /root/.gradle && echo -e "mavenUser=$mavenUser\nmavenPass=$mavenPass\n" > /root/.gradle/gradle.properties
RUN ./gradlew clean bootJar

FROM quay.io/energisk/launcher-java
ENV artifactId experian-service
ENV PROJECT_TYPE SpringBoot
WORKDIR /app
COPY --from=builder /src/build/libs/*.jar .

HEALTHCHECK --interval=10s --timeout=3s CMD wget --quiet --tries=1 --spider http://localhost:8080/actuator/health || exit 1
