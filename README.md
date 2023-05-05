# allane-mobility
Solution for a problem statement for leasing a vehicle

Pre-conditions:

**System requirements:**

1)OpenJDK for Java 11
2)Gradle
3)Junit
4)Project Lombok: https://projectlombok.org
5)OpenApi Swagger
6)Mysql
7)Docker
8)Flyway

**Building the project:**

./gradlew clean build

**To run the application:

./gradlew bootrun


Swagger UI: http://localhost:8080/swagger-ui.html

Install a docker engine.I used Docker for Windows: https://docs.docker.com/engine/install/

I have used Mysql as the chosen database and used a docker image to locally dockerize it.

To start the mysql server instance:

docker run --name allane-mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=my-secret-pw -d allane-mysql:0.1

Once running, can use the below command to execute commands in the container bash:

docker exec -it allane-mysql:0.1

mysql -u root -p

Password: my-secret-pw

Type show databases and you can see a database name allane along with others.


Sample payloads:

Vehicle:

{
"brand": "BMW",
"model": "X5",
"modelYear": 2020,
"vin":null,
"price": 45000
}

Customer:

{
"firstName": "Indranil",
"lastName": "Dey",
"birthDate": "1990-04-20"
}

Contract:

{
"monthlyRate": 200,
"customer": {
"id": 2,
"firstName": "Indranil",
"lastName": "Dey",
"birthDate": "2000-04-20"
},
"vehicle": {
"id": 3,
"brand": "Audi",
"model": "A5",
"modelYear": 2021,
"vin": null,
"price": 65000
}
}
