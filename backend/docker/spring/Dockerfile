# Этап сборки Spring Boot приложения
FROM maven:3.8.5-openjdk-17 AS spring-builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn package -DskipTests

# Финальный этап сборки
FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=spring-builder /app/target/backend-0.0.1-SNAPSHOT.jar ./app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
