FROM openjdk:17-jdk-alpine
LABEL maintainer="cumabolat@posta.mu.edu.tr"
VOLUME /tmp
EXPOSE 5000
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