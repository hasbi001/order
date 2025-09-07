FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY target/master-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]