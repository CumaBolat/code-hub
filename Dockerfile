FROM openjdk:17-jdk-alpine
LABEL maintainer="cumabolat@posta.mu.edu.tr"
VOLUME /tmp
EXPOSE 5000
ARG JAR_FILE=target/ide-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
ENV USER=java-ide-client
ENV GROUP=java-ide
ENV DIR=/home/$USER
RUN addgroup -S ${GROUP}
RUN adduser -S -s /bin/bash -G ${GROUP} ${USER}
RUN mkdir -p ${DIR}
RUN chown -R ${USER}:${GROUP} ${DIR}
RUN chmod 755 ${DIR}
USER ${USER}
WORKDIR ${DIR}