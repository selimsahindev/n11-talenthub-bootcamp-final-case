FROM openjdk:21-jdk

COPY target/api-gateway-1.0.0.jar api-gateway-1.0.0.jar

EXPOSE 8765:8765

ENTRYPOINT ["java","-jar","/api-gateway-1.0.0.jar"]