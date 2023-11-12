# --- BUILD ---
FROM amazoncorretto:20.0.2-alpine3.18 as build
WORKDIR /app
RUN apk add --no-cache maven
COPY pom.xml .
COPY src src
RUN mvn package -DskipTests

# --- RUN ---
FROM amazoncorretto:20.0.2-alpine3.18 as run
COPY --from=build /app/target/kmmk-backend.jar kmmk-backend.jar
COPY src/main/resources/application.yml application.yml
COPY src/main/resources/application-prod.yml application-prod.yml
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/kmmk-backend.jar"]
