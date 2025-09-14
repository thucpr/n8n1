#Build stage
FROM maven:3.8.7-openjdk-18 AS build
WORKDIR /build
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package

#Runtime stage
FROM amazoncorretto:17

WORKDIR /app
COPY --from=build /build/target/n8n-demo-*.jar /app/app.jar

EXPOSE 8080

CMD java -jar app.jar