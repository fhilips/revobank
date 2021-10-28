FROM openjdk:11
VOLUME /tmp
EXPOSE 8080
ADD ./target/revobank-0.0.1-SNAPSHOT.jar revobank.jar
ENTRYPOINT ["java","-jar","/revobank.jar"]