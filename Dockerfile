FROM maven:3-eclipse-temurin-24 AS build
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
COPY .env .

RUN mvn clean package -DskipTests

FROM eclipse-temurin:24-jre-alpine
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

COPY .env .

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]