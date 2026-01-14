FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
# Use the Jar from the GitHub Action
COPY target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]