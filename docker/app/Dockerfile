FROM openjdk:11

# Refer to Maven build -> finalName
ARG JAR_FILE=mongo-demo-0.0.1-SNAPSHOT.war

# cp target/spring-boot-web.jar /opt/app/app.jar
COPY ${JAR_FILE} app.war

#TODO replace this path to the config file
RUN mkdir -p /var/www/upload/avatars/
RUN chmod 777 -R /var/www/upload/avatars/

ENTRYPOINT java -jar app.war
