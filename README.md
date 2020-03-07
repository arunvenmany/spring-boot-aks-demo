# Handson of Building Java REST API using Spring Boot and MongoDB

Service Swagger can be accessed [here](http://spring-react-aks-app-service.eastasia.cloudapp.azure.com/swagger-ui.html)

[![Build Status](https://dev.azure.com/reactHandson/spring-react-aks-app/_apis/build/status/spring-boot-aks-app-Azure%20Kubernetes%20Service%20-CI?branchName=master)](https://dev.azure.com/reactHandson/spring-react-aks-app/_build/latest?definitionId=4&branchName=master)
## Available Scripts

In the project directory, you can run:


### `gradlew bootRun -Dspring.data.mongodb.password=<your mongo password>`

Runs the app in the development mode in local.<br>
Open [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) to view it in the browser.
The profile cloud is active by default, hence mongo will be pointing to cloud cluster


### `gradlew bootRun -Dspring.profiles.active=local`

Runs the app in the development mode in local with local database.<br>
Open [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) to view it in the browser.
Make sure the db is up and running


### `gradlew test`

Run Unit Tests in local.


### `gradlew build`

Builds your java jar file.
Go to build/libs folder and Run java -jar -Dspring.profiles.active=local spring-boot-aks-demo.jar to Run in local.


### `Build and Run in docker in local`

Build the Docker with Specified <docker_tag>

Note: Make sure that you have built your application jar file for your docker image using gradlew build.

 
```  
docker login http://aksspringreacthandson.azurecr.io -u aksspringreacthandson -p <password>

docker build -t aksspringreacthandson.azurecr.io/spring-aks-app:<docker_tag> .

docker run -p 8080:8080 aksspringreacthandson.azurecr.io/spring-aks-app:<docker_tag>

docker push aksspringreacthandson.azurecr.io/spring-aks-app:<docker_tag>
```
### `Deploy to Kubernetes from Local using Helm`
```
 helm upgrade --install spring-boot-aks-app ./infra/helm/ --install --set dockerTag=<docker_tag> --values infra/helm/values.yaml --set mongoPassword=<mongo_password> --namespace handson
Release "spring-boot-aks-app" does not exist. Installing it now.
NAME:   spring-boot-aks-app
NAMESPACE: handson
STATUS: DEPLOYED

RESOURCES:
==> v1/Pod(related)
NAME                                  READY  STATUS             RESTARTS  AGE
spring-boot-aks-app-5c98477468-58dvt  0/1    ContainerCreating  0         1s
spring-boot-aks-app-5c98477468-jw7r5  1/1    Terminating        0         1h
spring-boot-aks-app-5c98477468-kn5v5  1/1    Terminating        0         1h
spring-boot-aks-app-5c98477468-ngn9x  0/1    ContainerCreating  0         1s

==> v1/Service
NAME                     TYPE          CLUSTER-IP  EXTERNAL-IP  PORT(S)       AGE
spring-boot-aks-app-svc  LoadBalancer  10.0.87.45  <pending>    80:32133/TCP  1s

==> v1/Deployment
NAME                 DESIRED  CURRENT  UP-TO-DATE  AVAILABLE  AGE
spring-boot-aks-app  2        2        2           0          1s
```