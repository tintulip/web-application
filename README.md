# Web Application

This repo contains the source code for the scenario 1 workload.


## Building

### Required services

To run tests locally, you need a postgres database running, you can set it up with the following docker command:

```
docker run -d --rm -p5432:5432 -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=web_application_db --name postgres postgres
```

### Local Gradle build

You will need to have your personal credentials to Artifactory in the env named as follows:

```
export ARTIFACTORY_CI_USERNAME=<your username on https://tintulip.jfrog.io/>
export ARTIFACTORY_CI_PASSWORD=<your access token - grab it from https://tintulip.jfrog.io/ui/admin/artifactory/user_profile>
```

you can now run gradle commands like `./gradlew check` and `./gradlew clean bootJar`

Run the java test suite:

```
./gradlew test clean
```

Build the springboot (with embedded server) jar:

```
./gradlew bootJar
```

### Local Docker build

You will need to have your personal credentials to Artifactory in the env named as follows:

```
export artiUsername=<your username on https://tintulip.jfrog.io/>
export artiPassword=<your access token - grab it from https://tintulip.jfrog.io/ui/admin/artifactory/user_profile>
```

Then you need to `docker login tintulip.jfrog.io`

You are now able to build with:

`DOCKER_BUILDKIT=1 docker build --secret id=artiUsername --secret id=artiPassword -t tintulip-web-application:local .`

## Install Adopt OpenJDK 16 on MacOS with brew

```
brew tap adoptopenjdk/openjdk

brew install adoptopenjdk16
```

### Adding Adopt OpenJDK 16 to jenv

```
jenv add $(/usr/libexec/java_home)
```
