FROM openjdk:8
ENV APP_HOME=/usr/app/
ENV JAVA_OPTS=""
WORKDIR $APP_HOME
COPY build/libs/spring-boot-aks-demo.jar app.jar
EXPOSE 8080
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar app.jar" ]