FROM 905418194105.dkr.ecr.eu-central-1.amazonaws.com/cuma-bolat-docker-project:latest
EXPOSE 5000
ARG JAR_FILE=target/ide-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} /app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]