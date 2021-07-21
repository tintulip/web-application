FROM footw.jfrog.io/docker-remote/gradle:7.1.0-jdk16-openj9 AS build
WORKDIR /app
COPY build.gradle .
COPY settings.gradle .
COPY src src
RUN --mount=type=secret,id=artiUsername --mount=type=secret,id=artiPassword gradle clean bootJar

FROM footw.jfrog.io/docker-remote/adoptopenjdk/openjdk16:alpine-jre
EXPOSE 8080
RUN adduser -h /app/ -D -s /bin/sh developer
USER developer
WORKDIR /app
COPY --from=build /app/build/libs/web-application-*.jar /app/web-application.jar
ENTRYPOINT ["java","-server", "-Xms1G", "-Xmx1G", "-jar", "web-application.jar"]