# ================================
# Stage 1: Build the application
# ================================
FROM eclipse-temurin:17-jdk-jammy AS builder

WORKDIR /app

# Copy Maven wrapper and config files
COPY job-management/pom.xml .
COPY job-management/mvnw .
COPY job-management/.mvn .mvn

# Download dependencies
RUN ./mvnw dependency:go-offline -B

# Copy the source code
COPY job-management/src ./src

# Build the application
RUN ./mvnw clean package -DskipTests

# ================================
# Stage 2: Run the application
# ================================
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

# Copy the built JAR from the builder stage
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
