FROM openjdk:8-jre-slim
EXPOSE 8080
RUN useradd -d /app/ -U -m -s /bin/sh developer
USER developer
COPY build/libs/web-application-0.0.1-SNAPSHOT.jar /app/web-application.jar

WORKDIR /app

ENTRYPOINT ["java", "-server", "-Xms1G", "-Xmx1G", "-jar", "web-application.jar"]