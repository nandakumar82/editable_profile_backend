FROM openjdk:11-jdk-slim
VOLUME /tmp
COPY target/editable_profile-0.0.1-SNAPSHOT.jar target/editable_profile-0.0.1-SNAPSHOT.jar
ADD target/editable_profile-0.0.1-SNAPSHOT.jar editable_profile.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "editable_profile.jar"]