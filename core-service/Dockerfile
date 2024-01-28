FROM openjdk:21-jdk
COPY build/libs/core-service-0.0.1-SNAPSHOT.jar core.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "core.jar"]