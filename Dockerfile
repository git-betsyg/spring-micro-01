FROM eclipse-temurin:17-jre-alpine

ADD ./spring-micro-01/target/spring-micro-01-*.jar /app.jar

RUN sh -c 'touch /app.jar'
EXPOSE 8081

ENTRYPOINT ["java", "-jar", "/app.jar"]
