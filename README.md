# Application running instructions

## Alternative 1: Running the application using Docker Compose

Pre-requisites:
1. Docker
2. Docker Compose

To run ContactsApplication, you need to have Docker and Docker Compose installed on your machine. Once you have these installed, you can follow these steps:
1. Open a terminal and navigate to the project root directory.  
2. Run the following command to start all services (database and application) defined in the docker-compose.yml file:

`docker-compose up`

This command will start all the services: PostgresSQL database and the ContactsApplication. The application will be available at http://localhost:8080.
The database will be available at localhost:5432. Configuration for the database can be found in the application.properties file.

To stop the services, use command:

`docker-compose down`


## Alternative 2: Running the application using Maven and the database using Docker

Pre-requisites:
1. Java 21
2. Maven
3. Docker
4. Docker Compose

Instructions:

1. Run database using docker: 
`docker run --rm -d --name contacts-postgres -e POSTGRES_USER=contacts -e POSTGRES_PASSWORD=contacts -e POSTGRES_DB=contacts_db -p 5432:5432 postgres:latest`
2. Run `mvn clean install` to build the project
3. Run `mvn spring-boot:run` to start the application
4. The application will be available at http://localhost:8080 and the database will be available at localhost:5432.
5. To stop the application, use `Ctrl+C` in the terminal.
6. To stop the database, use `docker stop contacts-postgres` in the terminal.


