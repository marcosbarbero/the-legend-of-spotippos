The legend of Spotippos
---
It's a RESTful API manage Spotippos' Reign.

Technologies
---
This project was built with the following technologies:  
  
 - Maven 3
 - Java 8
 - Lombok
 - Spring Boot
 - H2 (InMemory)
 - Embedded Tomcat
    
Adding Project Lombok Agent
---

Spotippos project uses [Project Lombok](http://projectlombok.org/features/index.html)
to generate getters and setters etc. Compiling from the command line this
shouldn't cause any problems, but in an IDE you need to add an agent
to the JVM. Full instructions can be found in the Lombok website. The
sign that you need to do this is a lot of compiler errors to do with
missing methods and fields.


Build and Deploy
---
This application will generate an executable jar file, to build and run **ensure you have Java 8 and Maven 3 installed** 
and execute the following commands on the terminal:

```
$ mvn clean package
```
Once the build is finish execute:
```
$ java -jar target/spotippos.jar
```

Once it' built and running it will be available on port 8080.  
To make sure the application is running look for a similar message on STDOUT.
```
2016-05-04 13:13:35.736  INFO 37405 --- [           main] o.github.barbero.spotippos.Application   : Started Application in 6.559 seconds (JVM running for 6.989)
```

>**Note**: *Make sure you're on project root dir*.


API Documentation
---
Once the project is deployed you can reach the API docs in the following address: [/docs/api.html](http://localhost:8080/docs/api.html)

>**Note**: if you'e familiar with spring-boot and run the project with `mvn spring-boot:run` the docs won't be available once 
it's generated in mvn build phase.

Database
---
To access the database reach [/console](http://localhost:8080/console) from the browser 
and fill the fields with the following values:

 - Driver Class: `org.h2.Driver`
 - JDBC URL: `jdbc:h2:mem:testdb`
 - User Name: `sa`
 - Password: *empty value*
 
There will be four tables:
 
 - AXIS
 - BOUNDARIES
 - PROPERTY
 - PROVINCE
