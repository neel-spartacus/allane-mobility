# Use the official MySQL 8.0 image
FROM mysql:latest

# Set the default database name
ENV MYSQL_DATABASE=allane
ENV MYSQL_ROOT_PASSWORD my-secret-pw

# Copy a SQL script to initialize the database
COPY ./init.sql /docker-entrypoint-initdb.d/

# Expose the default MySQL port
EXPOSE 3306
