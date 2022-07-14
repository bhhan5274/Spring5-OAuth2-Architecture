FROM adoptopenjdk/openjdk11
ARG JAR_FILE_PATH=build/libs/federated-identity-authorization-server.jar
COPY ${JAR_FILE_PATH} app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
