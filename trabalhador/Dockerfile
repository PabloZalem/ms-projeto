FROM openjdk:11
VOLUME /tmp
ADD ./target/hr-worker-0.0.1-SNAPSHOT.jar trabalhador.jar
ENTRYPOINT ["java","-jar","/trabalhador.jar"]