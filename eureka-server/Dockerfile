FROM openjdk:21-jdk

COPY target/eureka-server-1.0.0.jar eureka-server-1.0.0.jar

EXPOSE 8761:8761

ENTRYPOINT ["java","-jar","/eureka-server-1.0.0.jar"]