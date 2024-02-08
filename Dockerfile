FROM docker.io/library/eclipse-temurin:21-jdk-alpine AS builder

WORKDIR /app
COPY . .
RUN chmod +x ./gradlew
RUN ./gradlew clean bootJar

FROM docker.io/library/eclipse-temurin:21-jdk-alpine AS runner

WORKDIR /app
COPY --from=builder /src/main/java/id/ac/ui/cs/advprog/eshop/*.jar app.jar
 
CMD ["java", "-jar", "app.jar"]