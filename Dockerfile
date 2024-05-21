FROM maven:3.6-jdk-8-slim as build
WORKDIR /home/app
COPY pom.xml .


RUN mvn dependency:go-offline

COPY src/ ./src
RUN mvn clean package
FROM openjdk:8-jre-slim

RUN addgroup concierge && adduser --no-create-home --disabled-password --ingroup concierge concierge
USER concierge

COPY --from=build /home/app/target/*.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]