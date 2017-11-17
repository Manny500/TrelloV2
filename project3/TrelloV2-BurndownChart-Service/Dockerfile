# base image
FROM maven:3.5

# app directory context
WORKDIR /app

# command line instructions 
RUN mvn --version

RUN mvn archetype:generate -DgroupId=com.docerdemo.app -DartifactId=docker-app -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false

RUN cd docker-app && mvn clean package

COPY . /app

# working directory
WORKDIR docker-app

CMD [ "java", "-cp", "target/docker-app-1.0-SNAPSHOT.jar", "com.docerdemo.app.App" ]