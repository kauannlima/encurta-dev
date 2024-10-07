FROM maven:3.8.7-openjdk-21-slim AS build

WORKDIR /build

COPY . .

RUN mvn clean package -DskipTests

FROM openjdk:21-jdk-slim

WORKDIR /app

COPY --from=build /build/target/EncurtaDev.jar /app/EncurtaDev.jar

CMD ["java", "-jar", "EncurtaDev.jar"]
