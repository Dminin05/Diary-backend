FROM openjdk:21-jdk
COPY build/libs/gateway-service-0.0.1-SNAPSHOT.jar gateway.jar
EXPOSE 5555
ENTRYPOINT ["java", "-jar", "gateway.jar"]