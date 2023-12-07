FROM openjdk:11.0.16
EXPOSE 8080:8080
RUN mkdir /app
COPY ./build/libs/*-all.jar /app/com.example.users-chat-all.jar
ENTRYPOINT ["java","-jar","/app/com.example.users-chat-all.jar"]