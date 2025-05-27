FROM openjdk:17-jdk

COPY target/chapecos-0.0.1-SNAPSHOT.jar /app/chapecos.jar

CMD ["java", "-jar", "/app/chapecos.jar"]

EXPOSE 8080