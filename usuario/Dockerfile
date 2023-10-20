FROM openjdk:11
VOLUME /tmp
ADD ./target/hr-user-0.0.1-SNAPSHOT.jar usuario.jar
ENTRYPOINT ["java","-jar","usuario.jar"]