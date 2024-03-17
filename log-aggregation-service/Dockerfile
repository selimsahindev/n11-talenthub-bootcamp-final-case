FROM openjdk:21-jdk

COPY target/log-aggregation-service-1.0.0.jar log-aggregation-service-1.0.0.jar

EXPOSE 8083:8083

ENTRYPOINT ["java","-jar","/log-aggregation-service-1.0.0.jar"]