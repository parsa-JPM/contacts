FROM openjdk:11

# Refer to Maven build -> finalName
ARG JAR_FILE=target/mongo-demo-0.0.1-SNAPSHOT.war

# cp target/spring-boot-web.jar /opt/app/app.jar
COPY ${JAR_FILE} app.war

ENTRYPOINT java -jar app.war
