# Handson of Building Java REST API using Spring Boot and MongoDB

## Available Scripts

In the project directory, you can run:


### `gradlew bootRun -Dspring.data.mongodb.password=<your mongo password>`

Runs the app in the development mode in local.<br>
Open [http://localhost:8080/swagger-ui](http://localhost:8080/swagger-ui) to view it in the browser.
The profile cloud is active by default, hence mongo will be pointing to cloud cluster


### `gradlew bootRun -Dspring.profiles.active=local`

Runs the app in the development mode in local with local database.<br>
Open [http://localhost:8080/swagger-ui](http://localhost:8080/swagger-ui) to view it in the browser.
Make sure the db is up and running

### `gradlew build`

Builds your java jar file
Go to build/libs folder and Run java -jar -Dspring.profiles.active=local spring-boot-aks-demo.jar to Run in local
