# ================================
# Stage 1: Build the application
# ================================
FROM eclipse-temurin:17-jdk-jammy AS builder

WORKDIR /app

# Copy Maven files and download dependencies
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn

# Download dependencies (for faster rebuilds)
RUN ./mvnw dependency:go-offline -B

# Copy the source code
COPY src ./src

# Build the application
RUN ./mvnw clean package -DskipTests

# ================================
# Stage 2: Run the application
# ================================
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

# Copy the built JAR from the builder stage
COPY --from=builder /app/target/*.jar app.jar

# Expose the port (Render will override it with $PORT)
EXPOSE 8080

# Run the Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]
