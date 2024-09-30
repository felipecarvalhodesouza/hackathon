FROM openjdk:22-oracle

WORKDIR /app

COPY target/hackathon-1.0.0-SNAPSHOT.jar /app/app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]