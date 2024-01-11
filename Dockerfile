FROM openjdk:21-jdk
COPY build/libs/Diary-backend-0.0.1-SNAPSHOT.jar main.jar
EXPOSE 8080
CMD ["java", "-jar", "main.jar"]