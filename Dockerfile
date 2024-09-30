FROM openjdk:21-oracle

WORKDIR /app

COPY target/hackathon-1.0.0.jar /app/app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]