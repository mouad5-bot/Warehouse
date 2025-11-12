FROM  maven:3.8.5-openjdk-17-slim AS build

WORKDIR /app

COPY ./pom.xml ./pom.xml

COPY ./src ./src

RUN mvn clean package  -DskipTests

FROM openjdk:17.0.1-jdk-slim

WORKDIR /app

COPY --from=build /app/target/warehouse-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 9999

CMD ["java", "-jar", "app.jar"]