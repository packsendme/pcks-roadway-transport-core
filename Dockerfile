
FROM openjdk:8-jdk-alpine
EXPOSE 9020
COPY /target/pcks-roadway-transport-core-0.0.1-SNAPSHOT.jar pcks-roadway-transport-core-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/pcks-roadway-transport-core-0.0.1-SNAPSHOT.jar"]