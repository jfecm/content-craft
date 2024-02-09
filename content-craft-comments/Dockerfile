FROM openjdk:17-jdk-slim
EXPOSE 8080
ADD target/content-craft-comments.jar content-craft-comments.jar
ENTRYPOINT ["java","-jar","/content-craft-comments.jar"]