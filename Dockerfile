# ------------------ BUILD STAGE ------------------
FROM eclipse-temurin:21-jdk AS builder
WORKDIR /app

# Copy Maven wrapper và set permissions
COPY mvnw .
COPY .mvn .mvn
RUN chmod +x mvnw

# Copy pom.xml và download dependencies (tận dụng Docker layer cache)
COPY pom.xml .
RUN ./mvnw dependency:go-offline

# Copy source code và build
COPY src src
RUN ./mvnw clean package -DskipTests

# ------------------ RUNTIME IMAGE ------------------
FROM gcr.io/distroless/java21-debian12:nonroot

WORKDIR /app

# Copy JAR file từ build stage
COPY --from=builder /app/target/*.jar app.jar

# Expose đúng port với application.yaml
EXPOSE 8081

# JVM options tối ưu cho container
ENTRYPOINT ["java", \
    "-XX:+UseZGC", \
    "-XX:+UseContainerSupport", \
    "-XX:MaxRAMPercentage=75.0", \
    "-XX:InitialRAMPercentage=50.0", \
    "-Djava.security.egd=file:/dev/./urandom", \
    "-jar", \
    "/app/app.jar"]