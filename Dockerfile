FROM eclipse-temurin:17-jdk-alpine
COPY build/libs/dev-tools-1.0.0.jar /app/dev-tools-1.0.0.jar
ENTRYPOINT ["java", "-XX:+UseZGC", "-Xms64m", "-Xmx64m", "-jar", "/app/dev-tools-1.0.0.jar"]