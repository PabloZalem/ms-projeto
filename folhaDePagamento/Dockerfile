FROM openjdk:11
VOLUME /tmp
ADD ./target/hr-payroll-0.0.1-SNAPSHOT.jar folhadepagamentos.jar
ENTRYPOINT ["java","-jar","folhadepagamentos.jar"]