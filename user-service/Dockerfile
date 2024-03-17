FROM openjdk:21-jdk

COPY target/user-service-1.0.0.jar user-service-1.0.0.jar

EXPOSE 8080:8080

ENTRYPOINT ["java","-jar","/user-service-1.0.0.jar"]