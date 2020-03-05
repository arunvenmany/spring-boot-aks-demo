FROM openjdk:8
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY ./build/libs/spring-boot-aks-demo.jar ./app.jar
EXPOSE 8080
CMD ["java","-jar","app.jar"]