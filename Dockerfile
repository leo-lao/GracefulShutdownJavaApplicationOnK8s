FROM openjdk:11
RUN mkdir -p /opt/app
WORKDIR /opt/app
ADD ./target/GracefulShutdownJavaApplicationOnK8s-0.01-jar-with-dependencies.jar GracefulShutdownJavaApplicationOnK8s-0.01-jar-with-dependencies.jar

CMD exec java -jar GracefulShutdownJavaApplicationOnK8s-0.01-jar-with-dependencies.jar