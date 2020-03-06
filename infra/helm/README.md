## Deploying using Helm

To deploy the app using Helm, simply run the following:


```
 helm upgrade --install spring-boot-aks-app ./infra/helm/ --install --set dockerTag=1.0.1 --values infra/helm/values.yaml --set mongoPassword=<mongo_password> --namespace handson
Release "spring-boot-aks-app" does not exist. Installing it now.
NAME:   spring-boot-aks-app
LAST DEPLOYED: Fri Mar  6 15:48:28 2020
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
