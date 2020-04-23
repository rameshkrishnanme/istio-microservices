FROM maven:3.3.9-jdk-8 as builder

RUN apt-get install -y curl

RUN ls -al /home/jenkins/agent

RUN mvn clean install /home/jenkins/agent

RUN ls -al target/

FROM openjdk:8-jdk-alpine
EXPOSE 8080

COPY --from=builder target/*.jar /app.jar
CMD java -XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap -Djava.security.egd=file:/dev/./urandom -jar /app.jar



#ARG JAR_FILE=target/istiorole-0.0.1-SNAPSHOT.jar
#ADD ${JAR_FILE} app.jar
#ENTRYPOINT ["java","-jar","app.jar"]