FROM openjdk:21-jdk
COPY build/libs/upload-service-0.0.1-SNAPSHOT.jar upload.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "upload.jar"]