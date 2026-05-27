FROM --platform=linux/amd64/v4 amazoncorretto:17-alpine-jdk AS build
ARG JAR_FILE=target/flight-service.jar
COPY ${JAR_FILE} flight-service.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/flight-service.jar"]