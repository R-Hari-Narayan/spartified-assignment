# Step 1: Use an official OpenJDK base image from Docker Hub.
FROM openjdk:22-jdk

# Step 2: Set the working directory inside the container.
WORKDIR /app

# Step 3: Copy the Spring Boot JAR file into the container.
# Replace 'target/my-spring-boot-app.jar' with the actual path to your JAR.

COPY target/spartified-assignment.jar /app/my-spring-boot-app.jar


# Step 5: Define the command to run your Spring Boot application.
ENTRYPOINT ["java", "-jar", "/app/my-spring-boot-app.jar"]