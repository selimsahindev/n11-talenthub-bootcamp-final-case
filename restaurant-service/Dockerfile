FROM openjdk:21-jdk

COPY target/restaurant-service-1.0.0.jar restaurant-service-1.0.0.jar

EXPOSE 8081:8081

ENTRYPOINT ["java","-jar","/restaurant-service-1.0.0.jar"]