# Web Application

This repo contains the source code for the scenario 1 workload.


## Building

You will need to have your personal credentials to Artifactory in the env named as follow:

```
export artiUsername=<your username on https://tintulip.jfrog.io/>
export artiPassword=<your access token - grab it from https://tintulip.jfrog.io/ui/admin/artifactory/user_profile>
```

Set up a local postgres database with the following docker command:

```
docker run -d --rm -p5432:5432 -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=web_application_db --name postgres postgres
```

First, run the java test suite:

```
./gradlew test clean
```

Build the springboot (with embedded server) jar:

```
./gradlew bootJar
```

## Install Adopt OpenJDK 16 on MacOS with brew

```
brew tap adoptopenjdk/openjdk

brew install adoptopenjdk16
```

### Adding Adopt OpenJDK 16 to jenv

```
jenv add $(/usr/libexec/java_home)
```
