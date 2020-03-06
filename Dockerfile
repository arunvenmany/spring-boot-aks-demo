FROM openjdk:8
ENV JAVA_OPTS=""
COPY /build/libs/spring-boot-aks-app.jar app.jar
EXPOSE 8080
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar app.jar" ]