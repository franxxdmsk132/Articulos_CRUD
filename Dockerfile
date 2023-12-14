FROM openjdk:11
COPY ./target/spring-boot-ems-app-1.0.jar /app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]