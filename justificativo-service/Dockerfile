FROM openjdk:17 
ARG JAR_FILE=target/justificativo-service.jar
COPY ${JAR_FILE} justificativo-service.jar
ENTRYPOINT ["java","-jar","/justificativo-service.jar"]