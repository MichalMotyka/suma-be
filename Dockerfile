FROM java:8-jdk
ADD out/artifacts/crm_be_jar/crm_be.jar .
EXPOSE 8080:8080
CMD ["java", "-jar", "crm-be.jar"]

