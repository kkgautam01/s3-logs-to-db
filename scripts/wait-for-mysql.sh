#!/bin/bash

# Wait for MySQL to be ready
echo "Waiting for MySQL to start..."

# Wait for MySQL server to become available using mysqladmin ping
until mysqladmin -h mysql -uroot -p${MYSQL_ROOT_PASSWORD} ping --silent; do
  echo "Waiting for MySQL to be ready..."
  sleep 5
done

echo "MySQL is ready. Running the .jar file."

# Run the .jar file
#java -jar /app/s3todb-1.0-SNAPSHOT.jar
