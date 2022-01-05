FROM openjdk:11

WORKDIR /opt/server
COPY ./target/service-comments-0.0.1-SNAPSHOT.jar server.jar

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "server.jar"]