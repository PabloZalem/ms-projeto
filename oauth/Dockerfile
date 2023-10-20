FROM openjdk:11
VOLUME /tmp
ADD ./target/hr-oauth-0.0.1-SNAPSHOT.jar oauth.jar
ENTRYPOINT ["java","-jar","oauth.jar"]