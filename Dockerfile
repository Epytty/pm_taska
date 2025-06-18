FROM maven:3.9.8 as build

WORKDIR /app
COPY pom.xml /app
RUN mvn dependency:resolve
COPY ./ /app
RUN mvn clean
RUN mvn package

FROM openjdk:17
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]