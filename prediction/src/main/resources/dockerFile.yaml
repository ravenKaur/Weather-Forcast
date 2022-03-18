FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/*.jar prediction-0.0.1-SNAPSHOT.jar.jar
ENTRYPOINT ["java","-jar","/prediction-0.0.1-SNAPSHOT.jar.jar"]