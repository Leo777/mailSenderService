FROM openjdk:8
ADD target/mailSenderService-0.0.1-SNAPSHOT.jar mailSenderService-0.0.1-SNAPSHOT.jar
EXPOSE 44321
ENTRYPOINT ["java", "-jar", "mailSenderService-0.0.1-SNAPSHOT.jar"]
