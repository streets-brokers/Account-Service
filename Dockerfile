FROM openjdk:11
EXPOSE 8084
ADD target/account_service.jar account_service.jar
ENTRYPOINT ["java", "-jar", "/account_service.jar"]
