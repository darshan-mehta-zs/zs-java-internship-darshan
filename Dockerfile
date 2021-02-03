FROM openjdk:11
ADD build/libs/SpringBootGradleProject-1.0-SNAPSHOT.jar  hobbies.jar
EXPOSE 8080
ENTRYPOINT ["java" ,"-jar" , "hobbies.jar"]