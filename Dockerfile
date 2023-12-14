FROM openjdk:17
COPY ./target/spring-boot-ems-app-0.0.1-SNAPSHOT.jar /app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]