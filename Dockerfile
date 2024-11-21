FROM openjdk:17
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Dserver.tomcat.relaxed-query-chars='^{}[]|<>\"' -Dserver.tomcat.relaxed-path-chars='<>[]{}|' -jar", "/app.jar"]
