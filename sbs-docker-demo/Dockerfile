FROM eclipse-temurin:17

LABEL mentainer="lukerajan.mathew@gmail.com"

WORKDIR /app

COPY target/sbs-docker-demo-0.0.1-SNAPSHOT.jar /app/sbs-docker-demo.jar

 ENTRYPOINT  ["java", "-jar", "sbs-docker-demo.jar"]

