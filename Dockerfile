
FROM openjdk:8-jdk-alpine
EXPOSE 9020
COPY /target/pcks-roadbrewa-transport-core-0.0.1-SNAPSHOT.jar pcks-roadbrewa-transport-core-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/pcks-roadbrewa-transport-core-0.0.1-SNAPSHOT.jar"]