# ---- Build stage ----
FROM maven:3.9.8-eclipse-temurin-17 AS builder
WORKDIR /BookBookingSystem
COPY pom.xml ./
RUN mvn -q -DskipTests dependency:go-offline
COPY src ./src
RUN mvn -q -DskipTests package

# ---- Runtime stage ----
FROM eclipse-temurin:17-jre-jammy
RUN useradd -ms /bin/bash spring
USER spring
WORKDIR /BookBookingSystem
COPY --from=builder /BookBookingSystem/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-XX:+UseContainerSupport","-XX:MaxRAMPercentage=75.0","-jar","/BookBookingSystem/app.jar"]
