FROM openjdk:17-jdk

COPY target/recommendation-service-1.0.0.jar recommendation-service-1.0.0.jar

EXPOSE 8082:8082

ENTRYPOINT ["java","-jar","/recommendation-service-1.0.0.jar"]