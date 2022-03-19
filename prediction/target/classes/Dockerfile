FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY prediction/target/prediction-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
