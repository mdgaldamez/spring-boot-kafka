FROM openjdk:18
VOLUME /tmp
EXPOSE 8082
ARG JAR_FILE=target/client-1.0.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]