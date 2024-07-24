FROM eclipse-temurin:17
LABEL maintainer="luke.lateral@gmail.com"
WORKDIR /app
COPY target/rest-crud-docker-0.0.1-SNAPSHOT.jar /app/rest-crud-docker.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=docker_prody", "-jar", "rest-crud-docker.jar"]
