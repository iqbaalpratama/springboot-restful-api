# Springboot Restful API Project

This project is Restful API that using springboot 3.1.6 and use in-memory h2 database.

The following is a sequence of commands to run this program locally:
 - Download `restful-1.0.0-SNAPSHOT.jar`  file from the target directory and 
 - Run the program using the command:  `restful-1.0.0-SNAPSHOT.jar` from cmd/powershell, wait until program run successfully.
 - After the program successfully running, you can access this documentation
1. [RESTful API basics: (getpostman.com)](https://documenter.getpostman.com/view/14219981/2s9Ykq6feQ): this API is about create tutorial system
2. [External API (getpostman.com)](https://documenter.getpostman.com/view/14219981/2s9Ykq6fih): this API is consumes external API from Kode-Pos-API from https://github.com/bachors/apiapi?tab=readme-ov-file#kemendag.
- To see in-memory database schema and executing queries to check the data, open `http://localhost:8081/h2-console/` in web browser and connect with this settings: 
`JDBC URL: jdbc:h2:mem:testdb`
`User name: sa`
