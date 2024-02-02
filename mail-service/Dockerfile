FROM openjdk:21-jdk
COPY build/libs/mail-service-0.0.1-SNAPSHOT.jar mail.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "mail.jar"]