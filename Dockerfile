FROM openjdk:11-jdk-slim
ADD target/editable_profile-0.0.1-SNAPSHOT.jar editable_profile.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "editable_profile.jar"]