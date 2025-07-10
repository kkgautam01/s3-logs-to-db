# Use an official MySQL 8 image as the base image
#FROM openjdk:21-jdk-slim

# Install MySQL client
#RUN apt-get update && apt-get install -y mysql-client
RUN apt-get update && apt-get install -y default-mysql-client


# Set the working directory inside the container
WORKDIR /app

# Copy the .jar file from the local machine to the container
COPY ./scripts/wait-for-mysql.sh /app/wait-for-mysql.sh

# Ensure the script is executable
RUN chmod +x /app/wait-for-mysql.sh

# Expose port for the application (if necessary)
#EXPOSE 8080

# Set the entrypoint to wait for MySQL and then run the Java application
CMD ["bash", "-c", "/app/wait-for-mysql.sh mysql 3306"]
