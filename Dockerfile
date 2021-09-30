FROM openjdk:11
COPY ./target/proposta-0.0.1-SNAPSHOT.jar app/proposta-0.0.1-SNAPSHOT.jar
WORKDIR /app
CMD ["java", "-jar", "proposta-0.0.1-SNAPSHOT.jar"]

