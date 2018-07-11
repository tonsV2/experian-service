FROM quay.io/energisk/builder-java AS builder
ARG mavenUser
ARG mavenPass
WORKDIR /src
ADD . .
# TODO: Move into builder-java
RUN mkdir /root/.gradle && echo "mavenUser=$mavenUser\nmavenPass=$mavenPass\n" > /root/.gradle/gradle.properties
RUN ./gradlew clean bootJar

FROM quay.io/energisk/launcher-java
WORKDIR /app
COPY --from=builder /src/build/libs/*-0.0.1-SNAPSHOT.jar .
CMD exec java -jar *-0.0.1-SNAPSHOT.jar
