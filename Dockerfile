FROM openjdk:11
VOLUME /tmp
ADD ./target/prueba-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]