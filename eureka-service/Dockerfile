FROM openjdk:21-jdk
COPY build/libs/eureka-service-0.0.1-SNAPSHOT.jar eureka.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "eureka.jar"]