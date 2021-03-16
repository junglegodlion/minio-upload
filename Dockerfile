#FROM java:8
FROM openjdk:8-jdk-alpine
ARG JAR_FILE
VOLUME /tmp
EXPOSE 9404
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]