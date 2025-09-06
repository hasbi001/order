FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY target/orderapi-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]