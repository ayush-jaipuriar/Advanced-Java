# Hello World
# Use the official Maven image as the base image
FROM maven:3.8.1-openjdk-11-slim as build

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml file to the container
COPY pom.xml .

# Download the project dependencies
RUN mvn dependency:go-offline

# Copy the rest of the project files to the container
COPY src ./src

# Build the project
RUN mvn package

# Use the OPENJDK image for the runtime environment
FROM openjdk:11-jre-slim

# Set the working directory in the container
WORKDIR /app

# Copy the built jar file from the build stage to the runtime environment
COPY --from=build /app/target/maven-demo-1.0-SNAPSHOT.jar ./maven-demo.jar

# Set the entry point for the container
ENTRYPOINT [ "java", "-jar", "maven-demo.jar" ]


