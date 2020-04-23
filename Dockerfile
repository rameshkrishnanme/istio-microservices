FROM maven:3.3.9-jdk-8 as builder

RUN apt-get install -y curl
RUN mkdir -p /workspace
WORKDIR /workspace
ADD . /workspace

RUN mvn clean install

RUN ls -al /workspace/target/


FROM openjdk:8-jdk-alpine
EXPOSE 8080

COPY --from=builder /workspace/target/*.jar /app.jar
CMD java -XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap -Djava.security.egd=file:/dev/./urandom -jar /app.jar



#ARG JAR_FILE=target/istiorole-0.0.1-SNAPSHOT.jar
#ADD ${JAR_FILE} app.jar
#ENTRYPOINT ["java","-jar","app.jar"]