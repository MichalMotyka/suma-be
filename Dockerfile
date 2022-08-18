FROM java:8-jdk
ADD target/crm-be-0.0.1-SNAPSHOT.jar .
EXPOSE 8080:8080
CMD ["java", "-jar", "crm-be-0.0.1-SNAPSHOT.jar"]

