FROM openjdk:8-jdk-alpine
COPY ./target/sortmovies-0.0.1.jar /app/sortmovies-0.0.1.jar
ENTRYPOINT ["java","-jar","sortmovies-0.0.1.jar"]
EXPOSE 8080

WORKDIR /app
CMD java -jar sortmovies-0.0.1.jar