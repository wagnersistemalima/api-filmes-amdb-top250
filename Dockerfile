FROM openjdk:17-alpine
EXPOSE 8080
ADD /target/filmes-0.0.1-SNAPSHOT.jar api-filme.jar
ENTRYPOINT ["java", "-jar", "api-filme.jar"]