Spring Boot with H2 Database Example
This is an example project that demonstrates how to use Spring Boot with an H2 database.

Run the following command to build the project:

mvn clean compile;

mvn spring-boot:run;

Open a web browser and go to the following URL to view the H2 console:
http://localhost:8088/h2-console, input jdbc:h2:mem:testdb into JDBC URL field

Enter the following information to connect to the database:
Driver Class: org.h2.Driver
JDBC URL: jdbc:h2:mem:testdb
User Name: sa
Password: (leave this field blank)
Click the "Connect" button to connect to the database.
