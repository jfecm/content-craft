FROM openjdk:17-jdk-slim
EXPOSE 8080
ADD target/content-craft-posts.jar content-craft-posts.jar
ENTRYPOINT ["java","-jar","/content-craft-posts.jar"]